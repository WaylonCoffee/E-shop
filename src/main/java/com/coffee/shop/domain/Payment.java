package com.coffee.shop.domain;

import com.coffee.shop.domain.enums.PayState;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 支付信息
 * @author waylon
 * @date 2018/08/10
 **/
@Data
@NoArgsConstructor
public class Payment {
    /**
     * 编号
     */
    private String id;
    /**
     * 订单编号
     */
    private String orderNo;
    /**
     * 支付日期
     */
    private Date paidDate;
    /**
     * 支付金额
     */
    private BigDecimal total;
    /**
     * 支付方式
     */
    private String method;
    /**
     * 支付状态
     */
    private PayState status;

}
