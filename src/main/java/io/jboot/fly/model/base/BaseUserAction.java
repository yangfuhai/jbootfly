package io.jboot.fly.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BaseUserAction<M extends BaseUserAction<M>> extends JbootModel<M> implements IBean {

    public static final String ACTION_ADD = "UserAction:add";
    public static final String ACTION_DELETE = "UserAction:delete";
    public static final String ACTION_UPDATE = "UserAction:update";


    @Override
    public String addAction() {
        return ACTION_ADD;
    }

    @Override
    public String deleteAction() {
        return ACTION_DELETE;
    }

    @Override
    public String updateAction() {
        return ACTION_UPDATE;
    }


	public void setId(java.lang.String id) {
		set("id", id);
	}
	
	public java.lang.String getId() {
		return getStr("id");
	}

	public void setUserId(java.lang.String userId) {
		set("user_id", userId);
	}
	
	public java.lang.String getUserId() {
		return getStr("user_id");
	}

	public void setAction(java.lang.String action) {
		set("action", action);
	}
	
	public java.lang.String getAction() {
		return getStr("action");
	}

	public void setPoint(java.lang.Integer point) {
		set("point", point);
	}
	
	public java.lang.Integer getPoint() {
		return getInt("point");
	}

	public void setPostId(java.lang.String postId) {
		set("post_id", postId);
	}
	
	public java.lang.String getPostId() {
		return getStr("post_id");
	}

	public void setCommentId(java.lang.String commentId) {
		set("comment_id", commentId);
	}
	
	public java.lang.String getCommentId() {
		return getStr("comment_id");
	}

	public void setCreated(java.util.Date created) {
		set("created", created);
	}
	
	public java.util.Date getCreated() {
		return get("created");
	}

	public void setModified(java.util.Date modified) {
		set("modified", modified);
	}
	
	public java.util.Date getModified() {
		return get("modified");
	}

}