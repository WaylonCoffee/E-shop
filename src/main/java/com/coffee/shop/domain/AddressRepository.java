package com.coffee.shop.domain;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 地址Repo
 * @author waylon
 * @date 2018/08/07
 **/
@Repository
public interface AddressRepository {

    /**
     * 获取客户地址
     * @param customerId
     * @return
     */
    @Select("SELECT id,customer_id,province,city,area,street,phone,consignee FROM address WHERE customer_id = #{customerId}")
    Address getAddressByCustomerId(String customerId);

}
