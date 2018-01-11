package io.jboot.fly.directive;

import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
import io.jboot.fly.model.Post;
import io.jboot.fly.service.CategoryService;
import io.jboot.fly.service.PostService;
import io.jboot.fly.service.UserService;
import io.jboot.utils.ArrayUtils;
import io.jboot.utils.StringUtils;
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
@JFinalDirective("topPosts")
public class TopPosts extends JbootDirectiveBase {

    @Inject
    PostService postService;

    @Inject
    UserService userService;

    @Inject
    CategoryService categoryService;

    @Override
    public void onRender(Env env, Scope scope, Writer writer) {
        int level = getParam("level", 1,scope);
        String categoryId = getParam("categoryId", scope);

        List<Post> posts = StringUtils.isBlank(categoryId) ? postService.findByLevel(level) : postService.findByLevelAndCategoryId(level, categoryId);
        userService.join(posts,"user_id");
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
