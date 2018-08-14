package com.coffee.shop.service;

import com.coffee.shop.component.message.Result;
import com.coffee.shop.component.message.ResultGenerator;
import com.coffee.shop.component.message.ServiceException;
import com.coffee.shop.component.util.ObjectId;
import com.coffee.shop.domain.*;
import com.coffee.shop.dto.CartItemForm;
import com.coffee.shop.dto.OrderModel;
import org.apache.commons.lang.StringUtils;
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
            throw new ServiceException("该商品不存在或已下架");
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


        if(cartItemForm.getQuantity() <= 0){
            throw new ServiceException("产品数量不能为0");
        }

        CartItem cartItem = cartItemRepo.getCartItemById(cartItemForm.getId());

        if(cartItem.getProduct()==null){
            throw new ServiceException("该商品已下架");
        }

        //库存判断
        int num = stockRepo.getStockBySpu(cartItem.getProduct().getSpu()).getNum();
        if(num <= cartItemForm.getQuantity()){
            throw new ServiceException("本商品库存不足");
        }

        //更新数量
        cartItem.setQuantity(cartItemForm.getQuantity());
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
                if(item.getProduct() == null){
                    messages.add(item.getId()+"商品已下架");
                    continue;
                }
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

        //用户地址
        Address address = addressRepo.getAddressByCustomerId(customerId);
        return ResultGenerator.genSuccessResult(new OrderModel(address,list));
    }

    /**
     * 直接结账
     * @param spu
     * @param customerId
     * @return
     */
    public Result checkout(String spu,String customerId){

        Product product = productRepo.getProductBySpu(spu);
        if(product == null){
            throw new ServiceException("商品不存在或已下架");
        }

        //查库存
        Stock stock = stockRepo.getStockBySpu(spu);
        if(stock.getNum() <= 0){
            throw new ServiceException("该商品无库存了");
        }

        //加入购物车
        Cart cart = cartRepo.getCartByCustomerId(customerId);
        CartItem cartItem = buildCartItem(cart,product);
        cartCacheService.addCartItem(cartItem,customerId);

        List<CartItem> list = new ArrayList<>();
        list.add(cartItem);

        //用户地址
        Address address = addressRepo.getAddressByCustomerId(customerId);
        return ResultGenerator.genSuccessResult(new OrderModel(address,list));

    }

    /**
     * 订单数据项构建
     * @param cart
     * @param product
     * @return
     */
    private CartItem buildCartItem(Cart cart ,Product product){
        CartItem cartItem = new CartItem();
        cartItem.setId(ObjectId.genGUID());
        cartItem.setProduct(product);
        cartItem.setCartId(cart.getId());
        cartItem.setQuantity(1);
        return cartItem;
    }

}