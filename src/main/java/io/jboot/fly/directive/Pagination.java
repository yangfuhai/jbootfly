package io.jboot.fly.directive;

import io.jboot.web.directive.JbootPaginateDirective;
import io.jboot.web.directive.annotation.JFinalDirective;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: 帖子分类
 * @Package io.jboot.fly.directive
 */
@JFinalDirective("pagination")
public class Pagination extends JbootPaginateDirective {

    @Override
    protected String getPageAttrName() {
        return "pageData";
    }
}
