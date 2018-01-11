package io.jboot.fly.service.impl;

import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import io.jboot.db.model.Columns;
import io.jboot.fly.model.UserCollection;
import io.jboot.fly.service.UserCollectionService;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class UserCollectionServiceImpl extends JbootServiceBase<UserCollection> implements UserCollectionService {

    @Override
    public UserCollection findByUserIdAndPostId(String userId, String postId) {

        Columns columns = Columns.create("user_id", userId).eq("post_id", postId);

        return DAO.findFirstByColumns(columns);
    }

    @Override
    public Page<UserCollection> paginateByUserId(int pageNumber, int pageSize, String userId) {
        Columns columns = Columns.create("user_id", userId);
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "created desc");
    }
}