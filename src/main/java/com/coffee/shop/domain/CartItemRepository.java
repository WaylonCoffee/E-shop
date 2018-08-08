package com.coffee.shop.domain;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 购物车项repo
 *
 * @author waylon
 * @date 2018/08/01
 **/
@Repository
public interface CartItemRepository {

    /**
     * 获取购物车子项
     * @param cartId
     * @return
     */
    @Select("SELECT id,quantity,spu FROM cart_item WHERE cart_id = #{cartId}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="spu",property="product",
                    one=@One(
                            select="com.coffee.shop.domain.ProductRepository.getProductBySpuWithoutImages",
                            fetchType=FetchType.EAGER
                    ))
    })
    List<CartItem> getCartItemsByCartId(String cartId);

    /**
     * 插入购物车子项
     * @param cartItem
     */
    @Insert("INSERT INTO cart_item (id,cart_id,spu,quantitu)VALUES(#{cartItem.id},#{cartItem.cartId},#{cartItem.product.spu},#{cartItem.quantity})")
    void addCartItem(CartItem cartItem);

    /**
     * 移除购物车选项
     * @param id
     */
    @Delete("DELETE FROM cart_item WHERE id = #{id}")
    void removeCartItem(@Param("id") String id);

    /**
     * 更新购物车子项
     * @param cartItem
     */
    @Update("UPDATE cart_item SET quantity = #{cartItem.quantity} WHERE id = #{cartItem.id}")
    void updateCartItem(CartItem cartItem);

    /**
     * 获取结账购物车子项集合
     * @param ids
     * @return
     */
    @Select("SELECT id,quantity,spu FROM cart_item WHERE id IN (${ids})")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="spu",property="product",
                    one=@One(
                            select="com.coffee.shop.domain.ProductRepository.getProductBySpuWithoutImages",
                            fetchType=FetchType.EAGER
                    ))
    })
    List<CartItem> getCartItemSelectedList(@Param("ids") String ids);

    /**
     * 批量移除结账子项
     * @param ids
     * @return
     */
    @Delete("DELETE FROM cart_item WHERE id in (${ids})")
    int removeCartItemSelected(@Param("ids") String ids);

}
