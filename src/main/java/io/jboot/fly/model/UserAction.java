package io.jboot.fly.model;

import io.jboot.db.annotation.Table;
import io.jboot.fly.model.base.BaseUserAction;

/**
 * 用户行为 及 得分记录
 */
@Table(tableName = "user_action", primaryKey = "id")
public class UserAction extends BaseUserAction<UserAction> {

    /**
     * 用户注册
     */
    public static final String ACTION_REGISTER = "register";

    /**
     * 用户发布帖子
     */
    public static final String ACTION_NEW_POST = "new_post";

    /**
     * 用户结贴
     */
    public static final String ACTION_FINISHED_POST = "finished_post";

    /**
     * 用户进行回帖
     */
    public static final String ACTION_NEW_COMMENT = "new_comment";


    /**
     * 回帖 被采纳了
     */
    public static final String ACTION_COMMENT_ADOPTED = "comment_adopted";

    /**
     * 用户签到
     */
    public static final String ACTION_SIGN_IN = "sign_in";


}
