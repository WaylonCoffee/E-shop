package com.coffee.shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 库存
 *
 * @author waylon
 * @date 2018/08/08
 **/
@Data
@NoArgsConstructor
public class Stock {

    /**
     * 商品编号
     */
    private String spu;

    /**
     * 商品数量
     */
    private Integer num;

    /**
     * 警戒数量
     */
    private Integer warnNum;

    /**
     * 总数
     */
    private Integer total;

}
