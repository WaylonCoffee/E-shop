package com.coffee.shop.domain;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 分类repo
 *
 * @author waylon
 * @date 2018/07/31
 **/
@Repository
public interface CategoryRepository {

    /**
     * 获取所有分类
     * @return
     */
    @Select("SELECT id,name FROM category")
    List<Category> getAllCategories();

}
