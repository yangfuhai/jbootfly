package io.jboot.fly.interceptor;

import io.jboot.fly.Constants;
import io.jboot.fly.manager.MessageManager;
import io.jboot.fly.manager.SigninManager;
import io.jboot.fly.model.User;
import io.jboot.fly.service.UserService;
import io.jboot.utils.EncryptCookieUtils;
import io.jboot.utils.StringUtils;
import io.jboot.web.fixedinterceptor.FixedInterceptor;
import io.jboot.web.fixedinterceptor.FixedInvocation;

import javax.inject.Inject;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Package io.jboot.fly.interceptor
 */
public class UserIntercepor implements FixedInterceptor {

    @Inject
    UserService userService;

    @Override
    public void intercept(FixedInvocation inv) {

        String userId = EncryptCookieUtils.get(inv.getController(), Constants.COOKIE_USER_ID);
        if (StringUtils.isNotBlank(userId)) {
            User user = userService.findById(userId);
            if (user != null) {
                inv.getController().setAttr(Constants.ATTR_USER, user);

                if (SigninManager.me().canSignin(userId)) {
                    inv.getController().setAttr("SGIN_FLAG", true);
                }

                if (MessageManager.me().hasMessage(userId)) {
                    inv.getController().setAttr("MESSAGE_FLAG", true);
                }
            }
        }

        inv.invoke();
    }
}
