package io.jboot.fly.controller;

import com.jfinal.aop.Before;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.fly.interceptor.NeedUser;
import io.jboot.fly.manager.ViewCountManager;
import io.jboot.fly.model.Category;
import io.jboot.fly.model.Comment;
import io.jboot.fly.model.Post;
import io.jboot.fly.service.CategoryService;
import io.jboot.fly.service.CommentService;
import io.jboot.fly.service.PostService;
import io.jboot.fly.service.UserService;
import io.jboot.fly.utils.JsoupUtils;
import io.jboot.utils.StringUtils;
import io.jboot.web.controller.annotation.RequestMapping;
import io.jboot.web.controller.validate.EmptyValidate;
import io.jboot.web.controller.validate.Form;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: 帖子的控制器
 * @Package io.jboot.fly.controller
 */
@RequestMapping(value = "/post", viewPath = "/WEB-INF/htmls/post")
public class PostController extends FlyControllerBase {

    @Inject
    PostService postService;

    @Inject
    CommentService commentService;

    @Inject
    UserService userService;

    @Inject
    CategoryService categoryService;


    /**
     * 显示帖子详情
     */
    public void index() {
        String id = getPara();
        if (StringUtils.isBlank(id)) {
            renderError(404);
            return;
        }

        Post post = postService.findById(id);
        if (post == null || !post.canShow()) {
            renderError(404);
            return;
        }

        // 记录文章浏览量
        ViewCountManager.me().incr(id);

        userService.join(post, "user_id");
        categoryService.join(post, "category_id");

        int pageNumber = getParaToInt("page", 1);
        pageNumber = pageNumber < 1 ? 1 : pageNumber;

        Page<Comment> page = commentService.paginateByPostId(pageNumber, 10, post.getId());
        userService.join(page, "user_id");

        setAttr("pageData", page);

        setAttr("post", post);
        render("detail.html");
    }

    /**
     * 显示发布页面
     */
    @Before(NeedUser.class)
    public void add() {
        keepPara();

        List<Category> categories = categoryService.findAll();
        setAttr("categories", categories);

        String id = getPara();
        if (StringUtils.isBlank(id)) {
            render("add.html");
            return;
        }


        Post post = postService.findById(id);
        if (post == null) {
            render("add.html");
            return;
        }

        if (!hasPostAuthority(post)) {
            renderError(404);
            return;
        }

        setAttr("post", post);
    }

    /**
     * 进行发布帖子
     */
    @Before(NeedUser.class)
    @EmptyValidate({
            @Form(name = "title", message = "标题不能为空"),
            @Form(name = "content", message = "内容不能为空"),
            @Form(name = "categoryId", message = "栏目选择不能为空"),
    })
    public void doAdd(String id, String title, String content, String categoryId, Integer point) {


        content = JsoupUtils.clear(content);
        title = JsoupUtils.getText(title);

        if (!validateCaptcha("vercode")) {
            setFlashAttr("message", "验证码错误");
            redirect("/post/add" + (id != null ? "/" + id : ""));
            return;
        }

        Post dbPost = null;
        if (id != null) {
            dbPost = postService.findById(id);
        }

        boolean updateAction = dbPost != null;

        if (!updateAction) {
            // 默认的元宝 选择没有低于20的
            if (point == null || point < 20) {
                setFlashAttr("message", "悬赏元宝选择错误");
                redirect("/post/add");
                return;
            }

            if (getLoginedUser().getPoint() == null || getLoginedUser().getPoint() < point) {
                setFlashAttr("message", "你的元宝不够，无法悬赏元宝");
                redirect("/post/add");
                return;
            }
        }


        final Post post = updateAction ? dbPost : new Post();
        post.setUserAgent(getUserAgent());
        post.setUserIp(getIPAddress());
        post.setTitle(title);
        post.setContent(content);
        post.setEditMode(Post.EDIT_MODE_HTML); //默认是html
        post.setCategoryId(categoryId);
        post.setCommentCount(0L);
        post.setStatus(Post.STATUS_NORMAL); //默认正常发帖

        if (!updateAction) {
            post.setUserId(getLoginedUser().getId());
            post.setId(StringUtils.uuid()); // 如果是在RPC默认下，这个ID是不会自动生成的，但是可以提前设置
            post.setPayPoint(point);
        }


        boolean success = updateAction ? postService.update(post) : postService.save(post);

        if (success) {
            setFlashAttr("message", "文章发布成功！");
            redirect("/post/" + post.getId());
        } else {
            setFlashAttr("message", "系统错误，请联系管理员。");
            redirect("/post/add");
        }
    }

    /**
     * 显示分类
     */
    public void category() {
        keepPara();
        int pageNumber = getParaToInt("page", 1);
        String categoryId = getPara();

        Integer status = getParaToInt("status");
        if (status != null && status != Post.STATUS_NORMAL && status != Post.STATUS_FINISHED) {
            status = null;
        }

        Integer r = getParaToInt("r");
        Boolean recommend = (r != null && r == 1) ? true : null;

        setAttr("status", status);
        setAttr("r", r);

        Page<Post> page = postService.paginateByCategoryId(pageNumber, 10, categoryId, status, recommend);

        Category category = categoryService.findById(categoryId);

        setAttr("category", category);

        userService.join(page, "user_id");

        setAttr("pageData", page);
        render("category.html");
    }


    /**
     * 进行删选的所有帖子
     */
    public void all() {
        int pageNumber = getParaToInt("page", 1);

        Integer status = getParaToInt("status");
        if (status != null && status != Post.STATUS_NORMAL && status != Post.STATUS_FINISHED) {
            status = null;
        }

        Integer r = getParaToInt("r");
        Boolean recommend = (r != null && r == 1) ? true : null;

        setAttr("status", status);
        setAttr("r", r);

        Page<Post> postPage = postService.paginate(pageNumber, 20, status, recommend);
        if (postPage == null || postPage.getPageNumber() > postPage.getTotalPage()) {
            renderError(404);
            return;
        }

        userService.join(postPage, "user_id");
        categoryService.join(postPage, "category_id");

        setAttr("pageData", postPage);
        render("all.html");
    }


}
