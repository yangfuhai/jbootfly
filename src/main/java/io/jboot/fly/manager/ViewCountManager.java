package io.jboot.fly.manager;

import io.jboot.Jboot;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: (请输入文件名称)
 * @Description: (用一句话描述该文件做什么)
 * @Package io.jboot.fly.manager
 */
public class ViewCountManager {

    private static final ViewCountManager me = new ViewCountManager();

    public static ViewCountManager me() {
        return me;
    }

    private ViewCountManager() {
        Jboot.injectMembers(this);
    }

    private Map<String, AtomicLong> views = new ConcurrentHashMap<>();

    public void incr(String postId) {
        AtomicLong count = views.get(postId);
        if (count == null) {
            count = new AtomicLong(1);
            views.put(postId, count);
        } else {
            count.incrementAndGet();
        }
    }


    public Map<String, AtomicLong> getViewsAndClear() {
        Map<String, AtomicLong> map = new HashMap<>(views);
        views.clear();
        return map;
    }
}
