package com.coffee.shop.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 地址
 *
 * @author waylon
 * @date 2018/08/07
 **/
@Data
@NoArgsConstructor
public class Address {
    /**
     * 编号
     */
    private String id;
    /**
     * 客户号
     */
    private String customerId;
    /**
     * 省
     */
    private String province;
    /**
     * 市
     */
    private String city;
    /**
     * 区
     */
    private String area;
    /**
     * 街道
     */
    private String street;
    /**
     * 联系手机
     */
    private String phone;
    /**
     * 收货人
     */
    private String consignee;

}
