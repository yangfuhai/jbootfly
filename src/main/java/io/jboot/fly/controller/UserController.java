package io.jboot.fly.controller;

import com.jfinal.aop.Before;
import com.jfinal.aop.Clear;
import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import io.jboot.fly.interceptor.NeedUser;
import io.jboot.fly.manager.MessageManager;
import io.jboot.fly.model.*;
import io.jboot.fly.service.*;
import io.jboot.fly.utils.AttachmentUtils;
import io.jboot.fly.utils.EncryptUtils;
import io.jboot.utils.StringUtils;
import io.jboot.web.controller.annotation.RequestMapping;
import org.joda.time.DateTime;

import javax.inject.Inject;
import java.io.File;
import java.util.List;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: 用户相关的控制器
 * @Package io.jboot.fly.controller
 */
@RequestMapping(value = "/user", viewPath = "/WEB-INF/htmls/user")
@Before(NeedUser.class)
public class UserController extends FlyControllerBase {


    @Inject
    UserService userService;

    @Inject
    UserActionService userActionService;

    @Inject
    PostService postService;

    @Inject
    UserMessageService userMessageService;

    @Inject
    CommentService commentService;

    @Inject
    UserCollectionService userCollectionService;


    /**
     * 显示 用户首页
     */
    @Clear()
    public void index() {
        String id = getPara();
        if (StringUtils.isBlank(id)) {
            renderError(404);
            return;
        }

        User user = userService.findById(id);
        if (user == null) {
            renderError(404);
            return;
        }

        //查询30天内的文章
        List<Post> posts = postService.findByUserIdAndCreated(user.getId(), DateTime.now().minusDays(30).toDate());
        setAttr("posts", posts);


        List<UserAction> userActions = userActionService.findByUserId(user.getId(), DateTime.now().minusDays(30).toDate());
        postService.join(userActions, "post_id");
        commentService.join(userActions, "comment_id");
        setAttr("userActions", userActions);

        setAttr("user", user);
        render("index.html");
    }

    /**
     * 显示用户设置页面
     */
    public void setting() {

    }


    public void doSetting(String nickname, String gender, String address, String site, String signature) {

        User user = getLoginedUser();
        user.setNickname(nickname);
        user.setGender(gender);
        user.setAddress(address);
        user.setSite(site);
        user.setSignature(signature);

        boolean success = userService.update(user);

        if (success) {
            renderJson(Ret.ok().set("message", "更新成功"));
        } else {
            renderJson(Ret.fail().set("message", "更新失败"));
        }
    }

    /**
     * 用户头像设置
     */
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
            getLoginedUser().setAvatar(path);
            userService.update(getLoginedUser());
            renderJson(Ret.ok().set("path", path).set("message", "上传成功"));
        } else {
            file.delete();
            renderFail("自定义图片请限制在2M以内!");
        }
    }


    public void resetpwd(String nowpassowrd, String password, String repassword) {

        User user = getLoginedUser();

        String uPwd = EncryptUtils.encryptPassword(nowpassowrd, user.getSalt());
        if (!uPwd.equals(user.getPassword())) {
            renderJson(Ret.fail("message", "密码错误").set("code", 5));
        }

        String salt = EncryptUtils.generateSalt();
        String encryptPassword = EncryptUtils.encryptPassword(password, salt);

        user.setSalt(salt);
        user.setPassword(encryptPassword);

        boolean success = userService.update(user);

        if (success) {
            renderJson(Ret.ok().set("message", "更新成功"));
        } else {
            renderJson(Ret.fail().set("message", "更新失败"));
        }
    }

    private void renderFail(String message) {
        renderJson(Ret.fail().set("message", message));
    }


    /**
     * 显示我发布的帖子
     */
    public void post() {
        int pageNumber = getParaToInt("page", 1);
        Page<Post> page = postService.paginateByUserId(pageNumber, 10, getLoginedUser().getId());

        userService.join(page, "user_id");

        setAttr("pageData", page);
        render("post.html");
    }


    /**
     * 显示： 我收藏的帖子
     */
    public void collection() {
        int pageNumber = getParaToInt("page", 1);
        Page<UserCollection> page = userCollectionService.paginateByUserId(pageNumber, 10, getLoginedUser().getId());

        userService.join(page, "user_id");

        setAttr("pageData", page);
        render("collection.html");
    }


    /**
     * 显示：我的消息
     */
    public void message() {
        int pageNumber = getParaToInt("page", 1);
        Page<UserMessage> page = userMessageService.paginateByUserId(pageNumber, 10, getLoginedUser().getId());

        userService.join(page, "from_user_id", "fromUser");
        postService.join(page, "post_id");
        commentService.join(page, "comment_id");

        MessageManager.me().clear(getLoginedUser().getId());

        setAttr("pageData", page);
        render("message.html");
    }


    /**
     * 邮箱激活
     */
    public void activate() {
        if (!getLoginedUser().isAdmin()) {
            renderError(404);
        }
    }

}
