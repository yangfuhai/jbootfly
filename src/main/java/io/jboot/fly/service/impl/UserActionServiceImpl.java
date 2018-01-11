package io.jboot.fly.service.impl;

import io.jboot.aop.annotation.Bean;
import io.jboot.db.model.Columns;
import io.jboot.fly.service.UserActionService;
import io.jboot.fly.model.UserAction;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;
import java.util.Date;
import java.util.List;

@Bean
@Singleton
public class UserActionServiceImpl extends JbootServiceBase<UserAction> implements UserActionService {

    @Override
    public List<UserAction> findByUserId(String userId, Date date) {
        Columns columns = Columns.create("user_id", userId)
                .ge("created", date);

        return DAO.findListByColumns(columns, "created desc");
    }

    @Override
    public List<UserAction> findByUserId(String userId, int count) {
        Columns columns = Columns.create("user_id", userId);
        return DAO.findListByColumns(columns, "created desc", count);
    }

    @Override
    public List<UserAction> findByUserIdAndAction(String userId, String action, Date date) {
        Columns columns = Columns.create("user_id", userId)
                .eq("action", action)
                .ge("created", date);

        return DAO.findListByColumns(columns, "created desc");
    }
}