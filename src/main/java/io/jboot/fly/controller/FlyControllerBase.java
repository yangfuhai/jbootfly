package io.jboot.fly.controller;

import io.jboot.fly.Constants;
import io.jboot.fly.model.Post;
import io.jboot.fly.model.User;
import io.jboot.web.controller.JbootController;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: (请输入文件名称)
 * @Description: (用一句话描述该文件做什么)
 * @Package io.jboot.fly.controller
 */
public class FlyControllerBase extends JbootController {

    /**
     * 获取 登陆用户
     *
     * @return
     */
    protected User getLoginedUser() {
        return getAttr(Constants.ATTR_USER);
    }

    /**
     * 拥有文章的操作权限
     * @param post
     * @return
     */
    protected boolean hasPostAuthority(Post post) {
        User user = getLoginedUser();
        if (user == null) {
            return false;
        }

        if (user.isAdmin()) {
            return true;
        }

        if (!user.isNormal()) {
            return false;
        }

        return post.getUserId().equals(user.getId());
    }
}
