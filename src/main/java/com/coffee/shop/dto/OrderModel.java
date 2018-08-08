package com.coffee.shop.dto;

import com.coffee.shop.domain.Address;
import com.coffee.shop.domain.CartItem;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单模板
 *
 * @author waylon
 * @date 2018/08/08
 **/
@Data
public class OrderModel {

    /**
     * 物流地址
     */
    Address address;
    /**
     * 购物项
     */
    List<CartItem> itemList;
    /**
     * 总金额
     */
    BigDecimal total;

    public BigDecimal getTotal(){
        this.total = getItemList().stream().map(x -> x.getProduct().getSalePrice().multiply(new BigDecimal(x.getQuantity()))).reduce(new BigDecimal(0),BigDecimal::add);
        return total;
    }

    public OrderModel(Address address, List<CartItem> itemList) {
        this.address = address;
        this.itemList = itemList;
    }
}
