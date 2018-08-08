package com.coffee.shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 购物车
 * @author waylon
 * @date 2018/08/01
 **/
@Data
@NoArgsConstructor
public class Cart {

    /**
     * 编号
     */
    private String id;

    /**
     * 客户编号
     */
    private String customerId;

    /**
     * 购物车选项
     */
    private List<CartItem> cartItemList;

    /**
     * 总金额
     */
    private BigDecimal total;

    public BigDecimal getTotal(){
        this.total = getCartItemList().stream().map(x -> x.getProduct().getSalePrice().multiply(new BigDecimal(x.getQuantity()))).reduce(new BigDecimal(0),BigDecimal::add);
        return total;
    }


}
