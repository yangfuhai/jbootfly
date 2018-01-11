package io.jboot.fly.controller;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.Jboot;
import io.jboot.fly.Constants;
import io.jboot.fly.FlyConfig;
import io.jboot.fly.model.Post;
import io.jboot.fly.model.User;
import io.jboot.fly.service.CategoryService;
import io.jboot.fly.service.PostService;
import io.jboot.fly.service.UserService;
import io.jboot.fly.utils.EncryptUtils;
import io.jboot.utils.EncryptCookieUtils;
import io.jboot.web.controller.JbootController;
import io.jboot.web.controller.annotation.RequestMapping;
import io.jboot.web.controller.validate.CaptchaValidate;
import io.jboot.web.controller.validate.EmptyValidate;
import io.jboot.web.controller.validate.Form;

import javax.inject.Inject;
import java.util.Date;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: 首页的控制器
 * @Package io.jboot.fly.controller
 */
@RequestMapping(value = "/", viewPath = "/WEB-INF/htmls")
public class IndexController extends JbootController {


    @Inject
    UserService userService;

    @Inject
    PostService postService;

    @Inject
    CategoryService categoryService;


    /**
     * 显示首页
     */
    public void index() {

        Page<Post> postPage = postService.paginate(1, 20, null, null);
        userService.join(postPage, "user_id");
        categoryService.join(postPage, "category_id");
        setAttr("pageData", postPage);
        render("index.html");
    }


    public void page() {
        Integer pageNumber = getParaToInt();
        if (pageNumber == null || pageNumber <= 1) {
            redirect("/");
            return;
        }

        Page<Post> postPage = postService.paginate(pageNumber, 20, null, null);
        if (postPage == null || postPage.getPageNumber() > postPage.getTotalPage()) {
            renderError(404);
            return;
        }

        userService.join(postPage, "user_id");
        categoryService.join(postPage, "category_id");

        setAttr("pageData", postPage);
        render("page.html");
    }


    /**
     * 显示登陆页面
     */
    public void login() {

    }

    /**
     * 进行登录操作
     */
    @EmptyValidate(value = {
            @Form(name = "email", message = "Email不能为空。"),
            @Form(name = "password", message = "密码不能为空。"),
    }, errorRedirect = "/login")
    @CaptchaValidate(form = "vercode", message = "验证码错误", errorRedirect = "/login")
    public void doLogin(String email, String password) {

        Ret ret = userService.doLogin(email, password);

        setFlashMap(ret);


        if (ret.isOk()) {
            User user = ret.getAs("user");
            user.setLogged(new Date());
            user.update();

            //发送用户登录 事件
            Jboot.sendEvent(Constants.ACTION_USER_LOGIN, user);

            //保存登录的cookie信息
            EncryptCookieUtils.put(this, Constants.COOKIE_USER_ID, user.getId());
        }

        redirect(ret.isOk() ? "/" : "/login");
    }

    /**
     * 退出登录
     */
    public void logout() {
        removeCookie(Constants.COOKIE_USER_ID);
        redirect("/login");
    }


    /**
     * 注册页面
     */
    public void register() {

    }


    /**
     * 进行注册操作
     */
    @EmptyValidate(value = {
            @Form(name = "email", message = "Email不能为空。"),
            @Form(name = "nickname", message = "昵称不能为空。"),
            @Form(name = "password", message = "密码不能为空。"),
            @Form(name = "repassword", message = "确认密码不能为空。"),
    }, errorRedirect = "/register")
    @CaptchaValidate(form = "vercode", message = "验证码错误", errorRedirect = "/regsiter")
    public void doRegister(String email, String nickname, String password, String repassword) {

        if (!password.equals(repassword)) {
            setFlashAttr("message", "密码为空 或 两次输入密码不相等");
            redirect("/register");
            return;
        }

        User user = userService.findByEmail(email);
        if (user != null) {
            setFlashAttr("message", "该邮箱已经存在");
            redirect("/register");
            return;
        }

        user = new User();
        user.setEmail(email);
        user.setNickname(nickname);
        user.setPoint((long) FlyConfig.get().getInitPoint());
        user.setStatus(User.STATUS_AUDITING); //审核中

        String salt = EncryptUtils.generateSalt();
        String encryptPassword = EncryptUtils.encryptPassword(password, salt);

        user.setSalt(salt);
        user.setPassword(encryptPassword);

        boolean success = userService.save(user);
        if (success) {
            setFlashAttr("message", "用户注册成功！请登录。");
            redirect("/active");
        } else {
            setFlashAttr("message", "系统错误，请联系管理员。");
            redirect("/register");
        }

    }


    /**
     * 回去密码页面
     */
    public void getpwd() {

    }

    /**
     * 进行取回密码操作
     */
    public void doGetpwd() {

    }

    public void captcha() {
        renderCaptcha();
    }


    /**
     * 需要激活
     */
    public void active() {

    }


}
