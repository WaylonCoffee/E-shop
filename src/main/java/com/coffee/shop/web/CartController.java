package com.coffee.shop.web;

import com.coffee.shop.component.message.Result;
import com.coffee.shop.dto.CartItemForm;
import com.coffee.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 购物车控制器
 * @author waylon
 * @date 2018/08/08
 **/
@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartService cartService;

    /**
     * 获取购物车信息
     * @param customerId
     * @return
     */
    @GetMapping("detail")
    public Result viewCart(@RequestParam String customerId){
        return cartService.viewCart(customerId);
    }

    /**
     * 添加购物车
     * @param spu
     * @param customerId
     * @return
     */
    @PostMapping("add")
    public Result addItem(@RequestParam String spu,@RequestParam String customerId){
        return cartService.addCartItem(spu,customerId);
    }

    /**
     * 编辑购物车
     * @param cartItemForm
     * @return
     */
    @PostMapping("edit")
    public Result updateItem(CartItemForm cartItemForm){
        return cartService.updateCartItem(cartItemForm);
    }

    /**
     * 删除购物车
     * @param id
     * @param customerId
     * @return
     */
    @PostMapping("delete")
    public Result removeItem(@RequestParam String id,@RequestParam String customerId){
        return cartService.removeCartItem(id,customerId);
    }

    /**
     * 结账
     * @param ids
     * @param customerId
     * @return
     */
    @GetMapping("checkout")
    public Result checkout(@RequestParam List<String> ids, @RequestParam String customerId){
        return cartService.checkout(ids,customerId);
    }



}
