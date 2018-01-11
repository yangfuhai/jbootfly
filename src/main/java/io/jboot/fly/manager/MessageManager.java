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
public class MessageManager {

    private static final MessageManager me = new MessageManager();

    public static MessageManager me() {
        return me;
    }


    private static final Object empty = new Object();

    private static LoadingCache<String, String> signins = Caffeine.newBuilder()
            .expireAfterWrite(10, TimeUnit.DAYS)
            .build(key -> null);


    public void message(String userId) {
        signins.put(userId, "");
    }

    public boolean hasMessage(String userId) {
        return signins.get(userId) != null;
    }

    public void clear(String userId) {
        signins.invalidate(userId);
    }


}
