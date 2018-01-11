package io.jboot.fly.directive;

import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
import io.jboot.fly.model.Post;
import io.jboot.fly.service.CategoryService;
import io.jboot.fly.service.PostService;
import io.jboot.fly.service.UserService;
import io.jboot.utils.ArrayUtils;
import io.jboot.web.directive.annotation.JFinalDirective;
import io.jboot.web.directive.base.JbootDirectiveBase;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: 置顶 帖子 列表
 * @Package io.jboot.fly.directive
 */
@JFinalDirective("hotPosts")
public class HotPosts extends JbootDirectiveBase {

    @Inject
    PostService postService;

    @Inject
    UserService userService;

    @Inject
    CategoryService categoryService;

    @Override
    public void onRender(Env env, Scope scope, Writer writer) {

        List<Post> posts = postService.findHotPosts();
        userService.join(posts, "user_id");
        categoryService.join(posts, "category_id");

        if (ArrayUtils.isNotEmpty(posts)) {
            scope.setLocal("posts", posts);
            renderBody(env, scope, writer);
        }

    }

    @Override
    public boolean hasEnd() {
        return true;
    }
}
