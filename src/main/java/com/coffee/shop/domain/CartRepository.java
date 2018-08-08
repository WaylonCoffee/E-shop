package com.coffee.shop.domain;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

/**
 *  购物车repo
 * @author waylon
 * @date 2018/08/01
 **/
@Repository
public interface CartRepository {

    /**
     * 获取购物车
     * @param customerId
     * @return
     */
    @Select("SELECT id,customer_id FROM cart WHERE customer_id = #{customerId}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="id",property="cartItemList",
                    many=@Many(
                            select="com.coffee.shop.domain.CartItemRepository.getCartItemsByCartId",
                            fetchType= FetchType.EAGER
                    )
            ),
    })
    Cart getCartByCustomerId(@Param("customerId") String customerId);



}
