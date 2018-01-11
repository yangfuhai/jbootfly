package io.jboot.fly.interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.kit.Ret;
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
public class ApiNeedUser implements Interceptor {

    @Inject
    UserService userService;

    @Override
    public void intercept(Invocation inv) {

        String userId = EncryptCookieUtils.get(inv.getController(), Constants.COOKIE_USER_ID);
        if (StringUtils.isBlank(userId)) {
            renderError(inv);
            return;
        }

        User user = userService.findById(userId);
        if (user == null) {
            renderError(inv);
            return;
        }

        inv.invoke();
    }

    private void renderError(Invocation inv) {
        inv.getController().renderJson(Ret.fail().set("message", "not logined").set("code", 886));
    }
}
