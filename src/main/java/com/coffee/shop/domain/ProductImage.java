package com.coffee.shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author waylon
 * @date 2018/07/31
 **/
@Data
@NoArgsConstructor
public class ProductImage {

    /**
     * 编号
     */
    private String id;

    /**
     * 图片链接
     */
    private String path;

    /**
     * 商品编号
     */
    private String spu;

}
