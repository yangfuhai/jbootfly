package io.jboot.fly.manager;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: (请输入文件名称)
 * @Description: (用一句话描述该文件做什么)
 * @Package io.jboot.fly.manager
 */
public class SigninManager {

    private static final SigninManager me = new SigninManager();

    public static SigninManager me() {
        return me;
    }


    private static final Object empty = new Object();

    private static LoadingCache<String, Long> signins = Caffeine.newBuilder()
            .expireAfterWrite(2, TimeUnit.HOURS)
            .build(key -> null);


    public boolean signin(String userId) {
        Long time = signins.get(userId);
        if (time == null) {
            signins.put(userId, System.currentTimeMillis());
            return true;
        }

        //间隔两个小时
        if (System.currentTimeMillis() - time > 1000 * 60 * 60 * 2) {
            signins.put(userId, System.currentTimeMillis());
            return true;
        }

        return false;
    }

    public boolean canSignin(String userId) {
        Long time = signins.get(userId);
        if (time == null) {
            return true;
        }

        return System.currentTimeMillis() - time > 1000 * 60 * 60 * 2;
    }


}
