package io.jboot.fly.service.impl;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import io.jboot.db.model.Columns;
import io.jboot.fly.FlyConfig;
import io.jboot.fly.model.Post;
import io.jboot.fly.model.UserAction;
import io.jboot.fly.service.PostService;
import io.jboot.fly.service.UserActionService;
import io.jboot.fly.service.UserService;
import io.jboot.service.JbootServiceBase;
import org.joda.time.DateTime;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Bean
@Singleton
public class PostServiceImpl extends JbootServiceBase<Post> implements PostService {


    @Inject
    UserService userService;

    @Inject
    UserActionService userActionService;

    @Override
    public List<Post> findByLevel(int level) {
        Columns columns = Columns.create("level", level).ge("status", 0);
        return DAO.findListByColumns(columns, "created desc");
    }

    @Override
    public List<Post> findByLevelAndCategoryId(int level, String categoryId) {
        Columns columns = Columns.create("category_id", categoryId).eq("level", level).ge("status", 0);
        return DAO.findListByColumns(columns, "created desc");
    }

    @Override
    public List<Post> findHotPosts() {
        Columns columns = Columns.create().ge("status", 0).ge("created", DateTime.now().minusDays(7).toDate());
        return DAO.findListByColumns(columns, "comment_count desc", 10);
    }

    @Override
    public List<Post> findByUserIdAndCreated(String userId, Date created) {
        Columns columns = Columns.create("user_id", userId).ge("status", 0).ge("created", created);
        return DAO.findListByColumns(columns, "created desc");
    }

    @Override
    public Page<Post> paginate(int pageNumber, int pageSize, Integer status, Boolean recommend) {
        Columns columns = Columns.create();

        if (status != null) {
            columns.eq("status", status);
        } else {
            columns.ge("status", 0);
        }

        if (recommend != null) {
            columns.eq("recommend", recommend ? 1 : 0);
        }

        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "created desc");
    }


    @Override
    public Page<Post> paginateByCategoryId(int pageNumber, int pageSize, String categoryId, Integer status, Boolean recommend) {
        Columns columns = Columns.create("category_id", categoryId);

        if (status != null) {
            columns.eq("status", status);
        } else {
            columns.ge("status", 0);
        }

        if (recommend != null) {
            columns.eq("recommend", recommend ? 1 : 0);
        }

        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "created desc");
    }

    @Override
    public Page<Post> paginateByUserId(int pageNumber, int pageSize, String userId) {
        Columns columns = Columns.create("user_id", userId);
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "created desc");
    }


    /**
     * 保存 帖子
     * <p>
     * 有三个逻辑：
     * 1、扣除用于的悬赏元宝
     * 2、增加 用户发布帖子 的奖励元宝
     * 3、生成 UserAction 记录用户 奖励信息
     *
     * @param post
     * @return
     */
    @Override
    public boolean save(Post post) {
        return Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                if (!post.save()) {
                    return false;
                }

                if (!userService.changePoint(post.getUserId(), -post.getPayPoint())) {
                    return false;
                }

                int newPostPoint = FlyConfig.get().getNewPostPoint();
                if (!userService.changePoint(post.getUserId(), newPostPoint)) {
                    return false;
                }

                UserAction userAction = new UserAction();
                userAction.setAction(UserAction.ACTION_NEW_POST);
                userAction.setUserId(post.getUserId());
                userAction.setPostId(post.getId());
                userAction.setPoint(newPostPoint);

                if (!userActionService.save(userAction)) {
                    return false;
                }

                return true;
            }
        });
    }
}