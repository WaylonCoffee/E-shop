package com.coffee.shop.domain;

import com.coffee.shop.domain.enums.ProductState;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 商品
 * @author waylon
 * @date 2018/07/31
 **/
@Data
@NoArgsConstructor
public class Product {

    /**
     * 商品编号
     */
    private String spu;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 销售价格
     */
    private BigDecimal salePrice;

    /**
     * 市场价格
     */
    private BigDecimal marketPrice;

    /**
     * 分类
     */
    private Category category;

    /**
     * 缩略图
     */
    private String thumbnail;

    /**
     * 商品详情
     */
    private String detail;

    /**
     * 图片集
     */
    private List<ProductImage> imageList;

    /**
     * 商品状态
     */
    private ProductState state;

    /**
     * 创建日期
     */
    private Date createDate;


}
