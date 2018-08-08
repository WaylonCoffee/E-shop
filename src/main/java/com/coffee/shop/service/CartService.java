package com.coffee.shop.service;

import com.coffee.shop.component.message.Result;
import com.coffee.shop.component.message.ResultGenerator;
import com.coffee.shop.component.message.ServiceException;
import com.coffee.shop.component.util.ObjectId;
import com.coffee.shop.domain.*;
import com.coffee.shop.dto.CartItemForm;
import com.coffee.shop.dto.OrderModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车服务
 *
 * @author waylon
 * @date 2018/08/03
 **/
@Service
public class CartService {

    @Autowired
    private CartCacheService cartCacheService;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private AddressRepository addressRepo;

    @Autowired
    private CartRepository cartRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private StockRepository stockRepo;

    /**
     * 查看购物车
     * @param customerId
     * @return
     */
    public Result viewCart(String customerId){
        return ResultGenerator.genSuccessResult(cartCacheService.getCartByCustomer(customerId));
    }

    /**
     * 加入购物车
     * @param spu
     * @param customerId
     * @return
     */
    public Result addCartItem(String spu,String customerId){

        Cart cart = cartRepo.getCartByCustomerId(customerId);
        Product product = productRepo.getProductBySpu(spu);

        //商品不存在或下架
        if(product == null){
            throw new ServiceException("该商品不存在或已下载");
        }

        //库存判断
        int num = stockRepo.getStockBySpu(spu).getNum();
        if(num <= 0){
            throw new ServiceException("本商品已无库存");
        }

        return ResultGenerator.genSuccessResult( cartCacheService.addCartItem(buildCartItem(cart,product),customerId));
    }

    /**
     * 更新购物车
     * @param cartItemForm
     */
    public Result updateCartItem(CartItemForm cartItemForm){
        CartItem cartItem = new CartItem();
        BeanUtils.copyProperties(cartItemForm,cartItem);
        if(cartItem.getQuantity() <= 0){
            throw new ServiceException("产品数量不能为0");
        }
        return ResultGenerator.genSuccessResult(cartCacheService.updateCartItem(cartItem,cartItemForm.getCustomerId()));
    }

    /**
     * 移除商品子项
     * @param id
     * @return
     */
    public Result removeCartItem(String id,String customerId){
        return ResultGenerator.genSuccessResult(cartCacheService.removeCartItem(id,customerId));
    }

    /**
     * 结账
     * @param idList
     * @param customerId
     * @return
     */
    public Result checkout(List<String> idList,String customerId){
        String ids = StringUtils.join(idList,",");

        List<CartItem> list = cartItemRepo.getCartItemSelectedList(ids);
        if(list == null){
            throw new ServiceException("购物车数据异常");
        }
        //库存判断
        String spus = list.stream().map(x -> x.getProduct().getSpu()).collect(Collectors.joining(","));
        List<Stock> stocks = stockRepo.getStockList(spus);

        List<String> messages = new ArrayList<>();
        for (CartItem item : list) {
            for (Stock stock : stocks){
                if(item.getProduct().getSpu().equals(stock.getSpu())){
                    if(item.getQuantity() > stock.getNum()){
                        messages.add(item.getProduct().getName()+"库存不足");
                    }
                }
            }
        }

        if(messages.size()>0){
            throw new ServiceException(messages.toString());
        }

        Address address = addressRepo.getAddressByCustomerId(customerId);
        return ResultGenerator.genSuccessResult(new OrderModel(address,list));
    }


    public CartItem buildCartItem(Cart cart ,Product product){
        CartItem cartItem = new CartItem();
        cartItem.setId(ObjectId.genGUID());
        cartItem.setProduct(product);
        cartItem.setCartId(cart.getId());
        cartItem.setQuantity(1);
        return cartItem;
    }

}