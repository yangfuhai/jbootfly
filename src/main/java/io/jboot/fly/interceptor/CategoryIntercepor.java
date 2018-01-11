//package io.jboot.fly.interceptor;
//
//import io.jboot.fly.Constants;
//import io.jboot.fly.model.Category;
//import io.jboot.fly.service.CategoryService;
//import io.jboot.web.fixedinterceptor.FixedInterceptor;
//import io.jboot.web.fixedinterceptor.FixedInvocation;
//
//import javax.inject.Inject;
//import java.util.List;
//
///**
// * @author Michael Yang 杨福海 （fuhai999@gmail.com）
// * @version V1.0
// * @Package io.jboot.fly.interceptor
// */
//public class CategoryIntercepor implements FixedInterceptor {
//
//    @Inject
//    CategoryService categoryService;
//
//    @Override
//    public void intercept(FixedInvocation inv) {
//
//        List<Category> list = categoryService.findAll();
//        inv.getController().setAttr(Constants.ATTR_CATEGORYS, list);
//
//        inv.invoke();
//    }
//}
