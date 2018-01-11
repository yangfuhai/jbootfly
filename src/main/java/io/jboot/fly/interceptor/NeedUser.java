package io.jboot.fly.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import io.jboot.fly.Constants;
import io.jboot.fly.model.User;
import io.jboot.fly.service.UserService;
import io.jboot.utils.EncryptCookieUtils;
import io.jboot.utils.StringUtils;

import javax.inject.Inject;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Package io.jboot.fly.interceptor
 */
public class NeedUser implements Interceptor {

    @Inject
    UserService userService;

    @Override
    public void intercept(Invocation inv) {

        String userId = EncryptCookieUtils.get(inv.getController(), Constants.COOKIE_USER_ID);
        if (StringUtils.isBlank(userId)) {
            redirectToLoginPage(inv);
            return;
        }

        User user = userService.findById(userId);
        if (user == null) {
            redirectToLoginPage(inv);
            return;
        }

        inv.invoke();
    }

    private void redirectToLoginPage(Invocation inv) {
        inv.getController().redirect("/login");
    }
}
