package io.jboot.fly;

import com.jfinal.config.Constants;
import io.jboot.Jboot;
import io.jboot.fly.interceptor.UserIntercepor;
import io.jboot.server.listener.JbootAppListenerBase;
import io.jboot.utils.EncryptCookieUtils;
import io.jboot.web.fixedinterceptor.FixedInterceptors;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Package io.jboot.fly
 */
public class App extends JbootAppListenerBase {

    public static void main(String args[]) {
        Jboot.run(args);
    }


    @Override
    public void onJfinalConstantConfig(Constants constants) {
        constants.setError404View("/WEB-INF/htmls/error/404.html");
    }

    @Override
    public void onFixedInterceptorConfig(FixedInterceptors fixedInterceptors) {
        fixedInterceptors.add(new UserIntercepor());
//        fixedInterceptors.add(new CategoryIntercepor());
    }

    @Override
    public void onJFinalStarted() {

        /**
         * 初始化 Cookie 的加密秘钥
         */
        EncryptCookieUtils.initEncryptKey(FlyConfig.get().getEncryptKey());
    }
}
