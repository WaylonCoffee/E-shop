package com.coffee.shop.domain;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 订单子项repo
 *
 * @author waylon
 * @date 2018/08/10
 **/
@Repository
public interface OrderItemRepository {

    /**
     * 订单项保存
     * @param item
     */
    @Insert("INSERT INTO order_item(id,order_no,spu,name,thumbnail,quantity,total)VALUES(#{item.id},#{item.orderNo},#{item.spu},#{item.name},#{item.thumbnail},#{item.quantity},#{item.total})")
    void saveItem(OrderItem item);

    /**
     * 批量保存订单项
     * @param item
     */
    @Insert("<script>" +
                "INSERT INTO order_item(id,order_no,spu,name,thumbnail,quantity,total)VALUES" +
                "<foreach item='item' index='index' collection='item' separator=','>" +
                    "(#{item.id},#{item.order_no},#{item.spu},#{item.name},#{item.thumbnail},#{item.quantity},#{item.total})" +
                "</foreach>" +
            "</script>")
    void saveItemList(List<OrderItem> item);

    /**
     * 获取订单子项
     * @param number
     * @return
     */
    @Select("SELECT id,order_no,spu,name,thumbnail,quantity,total FROM order_item WHERE order_no = #{number}")
    List<OrderItem> getOrderItemByOrderNo(@Param("number") String number);
}
