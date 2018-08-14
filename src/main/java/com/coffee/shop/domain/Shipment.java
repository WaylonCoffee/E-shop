package com.coffee.shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 物流信息
 *
 * @author waylon
 * @date 2018/08/10
 **/
@Data
@NoArgsConstructor
public class Shipment {
    /**
     * 编号
     */
    private String id;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 订单子项编号
     */
    private String itemId;
    /**
     * 收货人
     */
    private String shipTo;
    /**
     * 收货地址
     */
    private String address;
    /**
     * 联系手机
     */
    private String phone;
    /**
     * 物流单号
     */
    private String trackNo;
    /**
     * 物流名称
     */
    private String trackName;
    /**
     * 发货日期
     */
    private Date shipDate;

}
