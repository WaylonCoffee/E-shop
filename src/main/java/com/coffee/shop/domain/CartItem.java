package com.coffee.shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.Optional;

/**
 * 购物车选项
 *
 * @author waylon
 * @date 2018/08/01
 **/
@Data
@NoArgsConstructor
public class CartItem {

    /**
     * 编号
     */
    private String id;
    /**
     * 购物车id
     */
    private String cartId;
    /**
     * 商品
     */
    private Product product;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 总金额
     */
    private BigDecimal total;

    public BigDecimal getTotal(){
        Optional.ofNullable(product)
                .filter(x -> x.getSalePrice() != null)
                .map(x -> x.getSalePrice().multiply(new BigDecimal(quantity)))
                .ifPresent(x -> this.total = x);
        return total;
    }


}
