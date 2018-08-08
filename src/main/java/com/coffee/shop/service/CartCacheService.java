package com.coffee.shop.service;

import com.coffee.shop.domain.Cart;
import com.coffee.shop.domain.CartItem;
import com.coffee.shop.domain.CartItemRepository;
import com.coffee.shop.domain.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 购物车缓存服务
 *
 * @author waylon
 * @date 2018/08/06
 **/
@Service
public class CartCacheService {

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    /**
     * 获取个人购物车
     * @param customerId
     * @return
     */
    @Cacheable(value = "cart", key = "#customerId")
    public Cart getCartByCustomer(String customerId){
        return cartRepo.getCartByCustomerId(customerId);
    }

    /**
     * 更新购物车
     * @param cartItem
     * @param customerId
     * @return
     */
    @CachePut(value = "cart", key = "#customerId")
    public Cart updateCartItem(CartItem cartItem,String customerId){
        cartItemRepo.updateCartItem(cartItem);
        return cartRepo.getCartByCustomerId(customerId);
    }

    /**
     * 删除购物车子项
     * @param id
     * @param customerId
     * @return
     */
    @CachePut(value = "cart", key = "#customerId")
    public Cart removeCartItem(String id,String customerId){
        cartItemRepo.removeCartItem(id);
        return cartRepo.getCartByCustomerId(customerId);
    }

    /**
     *
     * @param cartItem
     * @param customerId
     * @return
     */
    @CachePut(value = "cart", key = "#customerId")
    public Cart addCartItem(CartItem cartItem,String customerId){
        cartItemRepo.addCartItem(cartItem);
        return cartRepo.getCartByCustomerId(customerId);
    }




}
