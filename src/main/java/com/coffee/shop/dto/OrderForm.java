package com.coffee.shop.dto;

import com.coffee.shop.domain.CartItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 订单表单
 *
 * @author waylon
 * @date 2018/08/13
 **/
@Data
@NoArgsConstructor
public class OrderForm {

    private List<CartItem> cartItems;


}
