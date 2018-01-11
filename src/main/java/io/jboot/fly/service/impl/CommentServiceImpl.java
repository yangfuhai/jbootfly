package io.jboot.fly.service.impl;

import com.jfinal.kit.Ret;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.IAtom;
import com.jfinal.plugin.activerecord.Page;
import io.jboot.aop.annotation.Bean;
import io.jboot.db.model.Columns;
import io.jboot.fly.FlyConfig;
import io.jboot.fly.model.Comment;
import io.jboot.fly.model.Post;
import io.jboot.fly.model.UserAction;
import io.jboot.fly.service.CommentService;
import io.jboot.fly.service.PostService;
import io.jboot.fly.service.UserActionService;
import io.jboot.fly.service.UserService;
import io.jboot.service.JbootServiceBase;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.sql.SQLException;

@Bean
@Singleton
public class CommentServiceImpl extends JbootServiceBase<Comment> implements CommentService {

    @Inject
    private PostService postService;

    @Inject
    private UserService userService;

    @Inject
    private UserActionService userActionService;

    @Override
    public Ret doAdoptComment(String commentId) {

        Comment comment = findById(commentId);
        if (comment == null) {
            return Ret.fail("message", "ID数据错误");
        }


        Post post = postService.findById(comment.getPostId());
        if (post == null) {
            return Ret.fail("message", "ID数据错误");
        }

        //已经结帖了
        if (post.isFinished()) {
            return Ret.fail("message", "已经结帖了");
        }

        post.setStatus(Post.STATUS_FINISHED); //设置帖子为：已经结帖
        comment.setStatus(Comment.STATUS_ADOPTED); //设置该条回复为：被采纳

        boolean success = Db.tx(new IAtom() {
            @Override
            public boolean run() throws SQLException {
                if (!post.update()) {
                    return false;
                }
                if (!comment.update()) {
                    return false;
                }


                // 结帖用户得分
                int finishedPostPoint = FlyConfig.get().getFinishedPostPoint();
                if (!userService.changePoint(post.getUserId(), finishedPostPoint)) {
                    return false;
                }

                UserAction finishedPostAction = new UserAction();
                finishedPostAction.setAction(UserAction.ACTION_FINISHED_POST);
                finishedPostAction.setUserId(post.getUserId());
                finishedPostAction.setPostId(post.getId());
                finishedPostAction.setPoint(finishedPostPoint);

                if (!userActionService.save(finishedPostAction)) {
                    return false;
                }


                //被采纳用户得分
                if (!userService.changePoint(comment.getUserId(), post.getPayPoint())) {
                    return false;
                }
                UserAction adoptComment = new UserAction();
                adoptComment.setAction(UserAction.ACTION_COMMENT_ADOPTED);
                adoptComment.setUserId(comment.getUserId());
                adoptComment.setPostId(post.getId());
                adoptComment.setCommentId(commentId);
                adoptComment.setPoint(finishedPostPoint);

                if (!userActionService.save(adoptComment)) {
                    return false;
                }


                return true;
            }
        });

        return success ? Ret.ok() : Ret.fail("message", "系统错误，请稍后再试");
    }

    @Override
    public Page<Comment> paginateByPostId(int pageNumber, int pageSize, String postId) {
        Columns columns = Columns.create("post_id", postId).ge("status", 0);
        return DAO.paginateByColumns(pageNumber, pageSize, columns.getList(), "created desc");
    }
}