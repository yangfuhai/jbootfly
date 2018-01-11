package io.jboot.fly.controller;

import com.jfinal.aop.Before;
import com.jfinal.kit.Ret;
import com.jfinal.upload.UploadFile;
import io.jboot.fly.FlyConfig;
import io.jboot.fly.interceptor.ApiNeedUser;
import io.jboot.fly.manager.CommentCountManager;
import io.jboot.fly.manager.MessageManager;
import io.jboot.fly.manager.SigninManager;
import io.jboot.fly.model.*;
import io.jboot.fly.service.*;
import io.jboot.fly.utils.AttachmentUtils;
import io.jboot.fly.utils.JsoupUtils;
import io.jboot.utils.StringUtils;
import io.jboot.web.controller.annotation.RequestMapping;
import io.jboot.web.controller.validate.EmptyValidate;
import io.jboot.web.controller.validate.Form;

import javax.inject.Inject;
import java.io.File;
import java.util.Date;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: ajax api 相关
 * @Package io.jboot.fly.controller.apis
 */
@RequestMapping("/api")
@Before(ApiNeedUser.class)
public class ApisController extends FlyControllerBase {

    @Inject
    private PostService postService;

    @Inject
    private CommentService commentService;

    @Inject
    private UserService userService;

    @Inject
    private UserActionService userActionService;

    @Inject
    private UserMessageService userMessageService;

    @Inject
    private UserCollectionService userCollectionService;


    /**
     * 对帖子进行搜藏
     */
    @EmptyValidate({
            @Form(name = "id", message = "帖子ID不能为空")
    })
    public void collectionAdd() {
        String id = getPara("id");
        Post post = postService.findById(id);
        if (post == null) {
            renderFail("id 数据错误");
            return;
        }

        UserCollection collection = userCollectionService.findByUserIdAndPostId(getLoginedUser().getId(), id);
        if (collection != null) {
            renderFail("您已经搜藏过了");
            return;
        }

        collection = new UserCollection();
        collection.setUserId(getLoginedUser().getId());
        collection.setPostId(id);
        collection.setPostTitle(post.getTitle());
        collection.setPostContent(post.getContent());
        collection.setPostUserId(post.getUserId());

        renderBack(userCollectionService.save(collection));
    }

    /**
     * 对帖子取消搜藏
     */
    @EmptyValidate({
            @Form(name = "id", message = "帖子ID不能为空")
    })
    public void collectionDel() {
        String id = getPara("id");
        Post post = postService.findById(id);
        if (post == null) {
            renderFail("id 数据错误");
            return;
        }

        UserCollection collection = userCollectionService.findByUserIdAndPostId(getLoginedUser().getId(), id);
        if (collection == null) {
            renderFail("您没搜藏过这篇帖子，无法删除");
            return;
        }

        renderBack(userCollectionService.delete(collection));
    }

    /**
     * 查询帖子是否已经被 "我" 搜藏了
     */
    @EmptyValidate({
            @Form(name = "id", message = "帖子ID不能为空")
    })
    public void collectionFind() {
        String id = getPara("id");
        Post post = postService.findById(id);
        if (post == null) {
            renderFail("id 数据错误");
            return;
        }

        UserCollection collection = userCollectionService.findByUserIdAndPostId(getLoginedUser().getId(), id);
        renderJson(Ret.ok().set("collection", collection != null));
    }

    /**
     * 删除帖子
     */
    @EmptyValidate({
            @Form(name = "id", message = "帖子ID不能为空")
    })
    public void postDel() {
        String id = getPara("id");
        Post post = postService.findById(id);
        if (post == null) {
            renderFail("id 数据错误");
            return;
        }

        //想通过接口, 删除别人的帖子 ?
        if (!hasPostAuthority(post)) {
            renderFail("你没有权限");
            return;
        }

        //超过2天，不能删除
        //管理员什么时候都可以删除
        if (!getLoginedUser().isAdmin() && new Date().getTime() - post.getCreated().getTime() > 1000 * 60 * 60 * 24) {
            renderFail("您的帖子超过2天了，不能删除");
            return;
        }

        renderBack(postService.delete(post));
    }


    /**
     * 帖子置顶
     */
    @EmptyValidate({
            @Form(name = "id", message = "帖子ID不能为空")
    })
    public void postLevel() {

        if (!getLoginedUser().isAdmin()) {
            renderFail("你没有权限操作");
            return;
        }

        String id = getPara("id");
        Post post = postService.findById(id);
        if (post == null) {
            renderFail("id 数据错误");
            return;
        }

        Integer level = getParaToInt("level");
        if (level == null || level < 0 || level > 3) {
            renderFail("Level数据错误");
            return;
        }

        post.setLevel(level);
        renderBack(postService.update(post));
    }


    /**
     * 帖子 加精
     */
    @EmptyValidate({
            @Form(name = "id", message = "帖子ID不能为空")
    })
    public void postRecommend() {

        if (!getLoginedUser().isAdmin()) {
            renderFail("你没有权限操作");
            return;
        }

        String id = getPara("id");
        Post post = postService.findById(id);
        if (post == null) {
            renderFail("id 数据错误");
            return;
        }

        post.setRecommend(true);
        renderBack(postService.update(post));
    }

    /**
     * 帖子 加精
     */
    @EmptyValidate({
            @Form(name = "id", message = "帖子ID不能为空")
    })
    public void postRecommendCancel() {

        if (!getLoginedUser().isAdmin()) {
            renderFail("你没有权限操作");
            return;
        }

        String id = getPara("id");
        Post post = postService.findById(id);
        if (post == null) {
            renderFail("id 数据错误");
            return;
        }

        post.setRecommend(null);
        renderBack(postService.update(post));
    }


    /**
     * 评论删除
     */
    @EmptyValidate({
            @Form(name = "id", message = "评论ID不能为空")
    })
    public void commentDel() {

        String id = getPara("id");

        Comment comment = commentService.findById(id);
        if (comment == null) {
            renderFail("id 数据错误");
            return;
        }

        Post post = postService.findById(comment.getPostId());

        /**
         * 必须满足如下才能删除
         * 1、管理员
         * 2、发评论的人
         * 3、发帖子的人
         */
        if (getLoginedUser().isAdmin()
                || getLoginedUser().getId().equals(comment.getUserId())
                || (post != null && getLoginedUser().getId().equals(post.getUserId()))) {

            CommentCountManager.me().decr(post.getId());
            renderBack(commentService.delete(comment));

        } else {
            renderFail("你没有权限操作");
        }
    }

    /**
     * 发布新评论
     */
    @EmptyValidate({
            @Form(name = "id", message = "帖子ID不能为空"),
            @Form(name = "content", message = "评论内容不能为空"),
    })
    public void commentAdd() {
        String id = getPara("id");
        Post post = postService.findById(id);
        if (post == null) {
            renderFail("id 数据错误");
            return;
        }

        String content = getPara("content");

        //保留基础的html内容，清除非安全的html内容，比如 <script>xxx</script>
        content = JsoupUtils.clear(content);

        Comment comment = new Comment();
        comment.setPostId(id);
        comment.setUserId(getLoginedUser().getId());
        comment.setContent(content);
        comment.setStatus(Comment.STATUS_NORMAL);
        comment.setUserIp(getIPAddress());
        comment.setUserAgent(getUserAgent());
        comment.setId(StringUtils.uuid());

        boolean success = commentService.save(comment);
        if (success) {
            CommentCountManager.me().incr(id);

            UserAction userAction = new UserAction();
            userAction.setPoint(FlyConfig.get().getNewCommentPoint());
            userAction.setAction(UserAction.ACTION_NEW_COMMENT);
            userAction.setCommentId(comment.getId());
            userAction.setPostId(id);
            userAction.setUserId(getLoginedUser().getId());
            userActionService.save(userAction);


            UserMessage userMessage = new UserMessage();
            userMessage.setFromUserId(getLoginedUser().getId());
            userMessage.setToUserId(post.getUserId());
            userMessage.setType(UserMessage.TYPE_COMMENT);
            userMessage.setPostId(post.getId());
            userMessage.setCommentId(comment.getId());

            userMessageService.save(userMessage);


            MessageManager.me().message(post.getUserId());
        }
        renderBack(success);
    }

    /**
     * 评论 被采纳，被采纳后会 结帖
     */
    @EmptyValidate({
            @Form(name = "id", message = "评论ID不能为空")
    })
    public void commentAdopt() {
        String id = getPara("id");

        Comment comment = commentService.findById(id);
        if (comment == null) {
            renderFail("id 数据错误");
            return;
        }

        Post post = postService.findById(comment.getPostId());
        if (post == null) {
            renderFail("id 数据错误");
            return;
        }

        if (!hasPostAuthority(post)) {
            renderFail("您没有权限操作");
            return;
        }

        Ret ret = commentService.doAdoptComment(id);
        renderJson(ret);
    }

    public void upload() {


        if (!isMultipartRequest()) {
            renderFail("不是MultipartRequest，不能上传文件");
            return;
        }

        UploadFile uploadFile = getFile();
        if (uploadFile == null) {
            renderFail("没有接收到任何文件上传");
            return;
        }
        File file = uploadFile.getFile();
        long size_check = 2 * 1024 * 1024;
        long usr_upLoad_file_size = file.length();
        if (usr_upLoad_file_size < size_check) {
            String path = AttachmentUtils.moveFile(uploadFile).replace("\\", "/");
            renderJson(Ret.ok().set("path", path).set("message", "上传成功"));
        } else {
            file.delete();
            renderFail("自定义图片请限制在2M以内!");
        }
    }

    /**
     * 签到
     */
    public void signin() {

        if (!SigninManager.me().signin(getLoginedUser().getId())) {
            renderFail("您已经签到过了，过会再来吧。");
            return;
        }

        UserAction userAction = new UserAction();
        userAction.setAction(UserAction.ACTION_SIGN_IN);
        userAction.setUserId(getLoginedUser().getId());
        userAction.setPoint(FlyConfig.get().getSignPoint());

        renderBack(userActionService.save(userAction));
    }


    /**
     * 评论 被采纳，被采纳后会 结帖
     */
    @EmptyValidate({
            @Form(name = "id", message = "评论ID不能为空")
    })
    public void messageDel() {
        String id = getPara("id");

        UserMessage userMessage = userMessageService.findById(id);
        if (userMessage == null) {
            renderFail("id 数据错误");
            return;
        }

        if (!userMessage.getToUserId().equals(getLoginedUser().getId())) {
            renderFail("id 数据错误");
            return;
        }

        renderBack(userMessageService.delete(userMessage));
    }

    @EmptyValidate({
            @Form(name = "email", message = "邮箱不能为空")
    })
    public void doActive(String email) {
        if (!getLoginedUser().isAdmin()) {
            renderFail("您没有权限");
            return;
        }

        User user = userService.findByEmail(email);
        if (user == null) {
            renderFail("没有找到该用户");
            return;
        }

        user.setStatus(User.STATUS_NORMAL);

        renderBack(userService.update(user));

    }


    public void messageDelAll() {
        renderBack(userMessageService.deleteByUserId(getLoginedUser().getId()));
    }


    private void renderBack(boolean success) {
        if (success) {
            renderJson(Ret.ok().set("message", "操作成功"));
        } else {
            renderJson(Ret.fail().set("message", "网络错误，请稍后再试"));
        }
    }


    private void renderFail(String message) {
        renderJson(Ret.fail().set("message", message));
    }

}
