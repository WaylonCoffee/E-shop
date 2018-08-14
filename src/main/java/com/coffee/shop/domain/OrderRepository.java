package com.coffee.shop.domain;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单repo
 *
 * @author waylon
 * @date 2018/08/10
 **/
@Repository
public interface OrderRepository {

    /**
     * 保存订单
     * @param order
     */
    @Insert("INSERT INTO `order` (number,customer_id,order_date,status,total)VALUES(#{order.number},#{order.customerId},now(),#{order.status},#{order.total})")
    void saveOrder(Order order);

    /**
     * 获取商品服务
     * @param customerId
     * @return
     */
    @Select("SELECT number,customer_id,order_date,status,total FROM `order` WHERE customer_id = #{customerId}")
    @Results({
            @Result(id=true,column="number",property="number"),
            @Result(column="number",property="items",
                    many=@Many(
                            select="com.coffee.shop.domain.OrderItemRepository.getOrderItemByOrderNo",
                            fetchType= FetchType.EAGER
                    )
            ),
            @Result(column="number",property="shipments",
                    many=@Many(
                            select="com.coffee.shop.domain.ShipmentRepository.getShipmentByOrderNo",
                            fetchType= FetchType.EAGER
                    )
            ),
    })
    List<Order> getOrderByCustomerId(@Param("customerId") String customerId);

}
