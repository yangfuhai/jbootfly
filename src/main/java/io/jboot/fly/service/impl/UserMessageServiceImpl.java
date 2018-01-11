package io.jboot.fly.service.impl;

import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import io.jboot.db.model.Columns;
import io.jboot.fly.model.UserMessage;
import io.jboot.fly.service.UserMessageService;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;

@Bean
@Singleton
public class UserMessageServiceImpl extends JbootServiceBase<UserMessage> implements UserMessageService {


    @Override
    public Page<UserMessage> paginateByUserId(int pageNumber, int pageSize, String userId) {
        Columns columns = Columns.create("to_user_id", userId);
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "created desc");
    }


    @Override
    public boolean deleteByUserId(String userId) {
        return Db.update("delete from user_message where to_user_id = ?", userId) > 0;
    }

}