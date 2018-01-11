package io.jboot.fly.cron;

import com.jfinal.plugin.activerecord.Db;
import io.jboot.fly.manager.CommentCountManager;
import io.jboot.fly.model.Post;
import io.jboot.schedule.annotation.Cron;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: 定时同步文章浏览量，每 1 分钟一次
 * @Package io.jboot.fly.cron
 */
@Cron("*/1 * * * *")
public class CommentCountSyncTask implements Runnable {

    private static Post postDao = new Post().dao();

    @Override
    public void run() {
        Map<String, AtomicLong> views = CommentCountManager.me().getCountAndClear();

        List<String> updateSqls = new ArrayList<>();
        for (Map.Entry<String, AtomicLong> entry : views.entrySet()) {
            if (entry.getValue().longValue() > 0) {
                String sql = "update `post` SET comment_count = comment_count + " + entry.getValue().longValue() + " where id = '" + entry.getKey() + "'";
                updateSqls.add(sql);
            } else {
                String sql = "update `post` SET comment_count = comment_count - " + Math.abs(entry.getValue().longValue()) + " where id = '" + entry.getKey() + "'";
                updateSqls.add(sql);
            }
        }

        Db.batch(updateSqls, 1000);

        // 更新文章浏览量后，清空缓存
        for (Map.Entry<String, AtomicLong> entry : views.entrySet()) {
            postDao.removeCache(entry.getKey());
        }
    }
}
