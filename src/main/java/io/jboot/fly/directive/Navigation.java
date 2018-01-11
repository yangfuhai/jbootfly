package io.jboot.fly.directive;

import com.jfinal.template.Env;
import com.jfinal.template.io.Writer;
import com.jfinal.template.stat.Scope;
import io.jboot.fly.model.Category;
import io.jboot.fly.service.CategoryService;
import io.jboot.utils.ArrayUtils;
import io.jboot.web.directive.annotation.JFinalDirective;
import io.jboot.web.directive.base.JbootDirectiveBase;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: 帖子分类
 * @Package io.jboot.fly.directive
 */
@JFinalDirective("navigation")
public class Navigation extends JbootDirectiveBase {

    @Inject
    CategoryService categoryService;

    @Override
    public void onRender(Env env, Scope scope, Writer writer) {

        List<Category> list = categoryService.findAll();

        if (ArrayUtils.isNotEmpty(list)) {
            scope.setLocal("categories", list);
            renderBody(env, scope, writer);
        }

    }

    @Override
    public boolean hasEnd() {
        return true;
    }
}
