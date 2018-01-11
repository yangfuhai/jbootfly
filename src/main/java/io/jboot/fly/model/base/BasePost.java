package io.jboot.fly.model.base;

import io.jboot.db.model.JbootModel;
import com.jfinal.plugin.activerecord.IBean;

/**
 * Generated by Jboot, do not modify this file.
 */
@SuppressWarnings("serial")
public abstract class BasePost<M extends BasePost<M>> extends JbootModel<M> implements IBean {

    public static final String ACTION_ADD = "Post:add";
    public static final String ACTION_DELETE = "Post:delete";
    public static final String ACTION_UPDATE = "Post:update";


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

	public void setTitle(java.lang.String title) {
		set("title", title);
	}
	
	public java.lang.String getTitle() {
		return getStr("title");
	}

	public void setContent(java.lang.String content) {
		set("content", content);
	}
	
	public java.lang.String getContent() {
		return getStr("content");
	}

	public void setEditMode(java.lang.String editMode) {
		set("edit_mode", editMode);
	}
	
	public java.lang.String getEditMode() {
		return getStr("edit_mode");
	}

	public void setCategoryId(java.lang.String categoryId) {
		set("category_id", categoryId);
	}
	
	public java.lang.String getCategoryId() {
		return getStr("category_id");
	}

	public void setUserId(java.lang.String userId) {
		set("user_id", userId);
	}
	
	public java.lang.String getUserId() {
		return getStr("user_id");
	}

	public void setUserIp(java.lang.String userIp) {
		set("user_ip", userIp);
	}
	
	public java.lang.String getUserIp() {
		return getStr("user_ip");
	}

	public void setUserAgent(java.lang.String userAgent) {
		set("user_agent", userAgent);
	}
	
	public java.lang.String getUserAgent() {
		return getStr("user_agent");
	}

	public void setVoteUp(java.lang.Long voteUp) {
		set("vote_up", voteUp);
	}
	
	public java.lang.Long getVoteUp() {
		return getLong("vote_up");
	}

	public void setVoteDown(java.lang.Long voteDown) {
		set("vote_down", voteDown);
	}
	
	public java.lang.Long getVoteDown() {
		return getLong("vote_down");
	}

	public void setPrice(java.math.BigDecimal price) {
		set("price", price);
	}
	
	public java.math.BigDecimal getPrice() {
		return get("price");
	}

	public void setPayPoint(java.lang.Integer payPoint) {
		set("pay_point", payPoint);
	}
	
	public java.lang.Integer getPayPoint() {
		return getInt("pay_point");
	}

	public void setCommentStatus(java.lang.String commentStatus) {
		set("comment_status", commentStatus);
	}
	
	public java.lang.String getCommentStatus() {
		return getStr("comment_status");
	}

	public void setCommentCount(java.lang.Long commentCount) {
		set("comment_count", commentCount);
	}
	
	public java.lang.Long getCommentCount() {
		return getLong("comment_count");
	}

	public void setCommentTime(java.util.Date commentTime) {
		set("comment_time", commentTime);
	}
	
	public java.util.Date getCommentTime() {
		return get("comment_time");
	}

	public void setViewCount(java.lang.Long viewCount) {
		set("view_count", viewCount);
	}
	
	public java.lang.Long getViewCount() {
		return getLong("view_count");
	}

	public void setRecommend(java.lang.Boolean recommend) {
		set("recommend", recommend);
	}
	
	public java.lang.Boolean getRecommend() {
		return get("recommend");
	}

	public void setLevel(java.lang.Integer level) {
		set("level", level);
	}
	
	public java.lang.Integer getLevel() {
		return getInt("level");
	}

	public void setStatus(java.lang.Integer status) {
		set("status", status);
	}
	
	public java.lang.Integer getStatus() {
		return getInt("status");
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
