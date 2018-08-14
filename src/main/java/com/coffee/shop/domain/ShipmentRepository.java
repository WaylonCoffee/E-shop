package com.coffee.shop.domain;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 物流repo
 * @author waylon
 * @date 2018/08/14
 **/
@Repository
public interface ShipmentRepository {

    /**
     * 获取物流信息
     * @param orderNo
     * @return
     */
    @Select("SELECT track_no,track_name,ship_date FROM shipment WHERE order_no = #{orderNo}")
    List<Shipment> getShipmentByOrderNo(@Param("orderNo") String orderNo);

}
