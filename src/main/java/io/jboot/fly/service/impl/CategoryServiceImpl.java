package io.jboot.fly.service.impl;

import io.jboot.aop.annotation.Bean;
import io.jboot.core.cache.annotation.Cacheable;
import io.jboot.fly.service.CategoryService;
import io.jboot.fly.model.Category;
import io.jboot.service.JbootServiceBase;

import javax.inject.Singleton;
import java.util.List;

@Bean
@Singleton
public class CategoryServiceImpl extends JbootServiceBase<Category> implements CategoryService {

    @Override
    @Cacheable(name = "category", key = "all")
    public List<Category> findAll() {
        String sql = "select * from `category` order by order_number asc";
        return DAO.find(sql);
    }
}