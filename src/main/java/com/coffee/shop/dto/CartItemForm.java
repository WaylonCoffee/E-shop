package com.coffee.shop.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 *  购物车选项表单
 * @author waylon
 * @date 2018/08/06
 **/
@Data
@NoArgsConstructor
public class CartItemForm {

    /**
     * 子项编号
     */
    @NotNull
    private String id;

    /**
     * 数量
     */
    @NotNull
    private Integer quantity;

    /**
     * 会员编号
     */
    @NotNull
    private String customerId;

}
