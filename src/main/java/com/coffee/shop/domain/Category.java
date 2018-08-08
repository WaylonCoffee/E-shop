package com.coffee.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 商品分类
 * @author waylon
 * @date 2018/07/31
 **/
@Setter
@Getter
@NoArgsConstructor
public class Category {

    /**
     * 编号
     */
    private String id;

    /**
     * 名称
     */
    private String name;

}
