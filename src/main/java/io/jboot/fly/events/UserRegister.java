package io.jboot.fly.events;

import io.jboot.event.JbootEvent;
import io.jboot.event.JbootEventListener;
import io.jboot.event.annotation.EventConfig;
import io.jboot.fly.model.User;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: 监听新用户注册
 * @Description: (用一句话描述该文件做什么)
 * @Package io.jboot.fly.events
 */
@EventConfig(action = {User.ACTION_ADD})
public class UserRegister implements JbootEventListener {

    @Override
    public void onEvent(JbootEvent event) {
        User user = event.getData();

        String email = user.getEmail();
    }
}
