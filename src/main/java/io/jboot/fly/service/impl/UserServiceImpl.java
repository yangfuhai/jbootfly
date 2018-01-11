package io.jboot.fly.service.impl;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import io.jboot.aop.annotation.Bean;
import io.jboot.core.cache.annotation.Cacheable;
import io.jboot.fly.model.User;
import io.jboot.fly.service.UserService;
import io.jboot.fly.utils.EncryptUtils;
import io.jboot.service.JbootServiceBase;
import io.jboot.utils.StringUtils;

import javax.inject.Singleton;

@Bean
@Singleton
public class UserServiceImpl extends JbootServiceBase<User> implements UserService {

    @Override
    public Ret doLogin(String email, String password) {
        if (StringUtils.isBlank(email)) {
            return Ret.fail("message", "账号不能为空").set("code", 1);
        }

        if (StringUtils.isBlank(password)) {
            return Ret.fail("message", "密码不能为空").set("code", 2);
        }

        User user = findByEmail(email);
        if (user == null) {
            return Ret.fail("message", "没有该用户").set("code", 3);
        }

        if (!user.isNormal()) {
            return Ret.fail("message", "您的账号在审核中或被冻结，无法登陆").set("code", 4);
        }


        String uPwd = EncryptUtils.encryptPassword(password, user.getSalt());
        if (!uPwd.equals(user.getPassword())) {
            return Ret.fail("message", "密码错误").set("code", 5);
        }


        return Ret.ok("user", user);
    }

    /**
     * 根据email 查询用户信息，会自动缓存，缓存的key为：email的值
     *
     * @param email
     * @return
     */
    @Cacheable(name = "user", key = "#(email)")
    @Override
    public User findByEmail(String email) {
        return DAO.findFirstByColumn("email", email);
    }


    @Override
    public boolean changePoint(String userId, int point) {
        String sql = "update `user` SET point = point + ? where id = ? ";
        return Db.update(sql, point, userId) > 0;
    }

}