package com.coffee.shop.domain;

import com.coffee.shop.domain.enums.OrderState;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单
 * @author waylon
 * @date 2018/08/08
 **/
@Data
@NoArgsConstructor
public class Order {

    /**
     * 订单编号
     */
    private String number;
    /**
     * 客户ID
     */
    private String customerId;
    /**
     * 订单日期
     */
    private Date orderDate;
    /**
     * 订单日期
     */
    private OrderState status;
    /**
     * 订单总金额
     */
    private BigDecimal total;
    /**
     * 订单项
     */
    private List<OrderItem> items;
    /**
     * 物流信息
     */
    private List<Shipment> shipments;



}
