package com.coffee.shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 订单项
 *
 * @author waylon
 * @date 2018/08/10
 **/
@Data
@NoArgsConstructor
public class OrderItem {
    /**
     * 编号
     */
    private String id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 商品编号
     */
    private String spu;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 缩略图
     */
    private String thumbnail;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 价格
     */
    private BigDecimal total;
}
