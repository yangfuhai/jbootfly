package io.jboot.fly.directive;

import io.jboot.utils.StringUtils;
import io.jboot.web.JbootRequestContext;
import io.jboot.web.directive.JbootPaginateDirective;
import io.jboot.web.directive.annotation.JFinalDirective;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Michael Yang 杨福海 （fuhai999@gmail.com）
 * @version V1.0
 * @Title: 帖子分类
 * @Package io.jboot.fly.directive
 */
@JFinalDirective("indexPage")
public class IndexPage extends JbootPaginateDirective {

    @Override
    protected String getUrl(int pageNumber) {
        HttpServletRequest request = JbootRequestContext.getRequest();
        String queryString = request.getQueryString();
        String url = request.getRequestURI();
        url = url.substring(0, url.lastIndexOf("/")) + "/" + pageNumber;

        if (StringUtils.isNotBlank(queryString)) {
            url.concat("?").concat(queryString);
        }

        return url;
    }

    @Override
    protected String getPageAttrName() {
        return "pageData";
    }
}
