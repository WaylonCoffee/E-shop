package com.coffee.shop.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 收银信息
 *
 * @author waylon
 * @date 2018/08/14
 **/
@Data
public class CashierModel {
    private String orderNo;
    private BigDecimal total;

    public CashierModel(){}

    public CashierModel(String orderNo, BigDecimal total) {
        this.orderNo = orderNo;
        this.total = total;
    }
}
