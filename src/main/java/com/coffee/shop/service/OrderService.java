package com.coffee.shop.service;

import com.coffee.shop.component.message.Result;
import com.coffee.shop.component.message.ResultGenerator;
import com.coffee.shop.component.message.ServiceException;
import com.coffee.shop.component.util.ObjectId;
import com.coffee.shop.domain.*;
import com.coffee.shop.domain.enums.OrderState;
import com.coffee.shop.dto.CashierModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 订单服务
 *
 * @author waylon
 * @date 2018/08/13
 **/
@Service
@PropertySource("")
public class OrderService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private StockRepository stockRepo;

    @Autowired
    private CartItemRepository cartItemRepo;

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private OrderRepository orderRepo;

    private static final String ORDER_NUM_KEY = "orderNum::";

    @Value("${shop.id}")
    private String shopId;

    /**
     * 购物车下单
     * @param spuList
     * @param itemIdList
     * @param customerId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Result placeOrder(List<String> spuList,List<String> itemIdList, String customerId){

        //查库存
        String spus = StringUtils.join(spuList,",");
        List<Stock> stocks = stockRepo.getStockList(spus);

        //查购物信息
        String itemIds = StringUtils.join(itemIdList,",");
        List<CartItem> cartItems = cartItemRepo.getCartItemSelectedList(itemIds);

        //库存匹配
        List<String> messages = new ArrayList<>();
        for (CartItem item : cartItems) {
            for (Stock stock : stocks) {
                if (item.getProduct().getSpu().equals(stock.getSpu())){
                    if(item.getProduct() == null){
                        messages.add(item.getId()+"商品已下架");
                        continue;
                    }
                    if (item.getQuantity() > stock.getNum()) {
                        messages.add(item.getProduct().getName() + "库存不足");
                    }
                }
            }
        }

        if(messages.size()>0){
            throw new ServiceException(messages.toString());
        }

        //创建订单
        Order order = createOrder(customerId,cartItems);

        //购物车清除
        cartItemRepo.removeCartItemSelected(itemIds);
        return ResultGenerator.genSuccessResult(new CashierModel(order.getNumber(),order.getTotal()));
    }

    /**
     * 订单前缀 年后两位 + 一年第几天 + 一天第几个小时
     * @return
     */
    private String genOrderNoPre(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String  year = calendar.get(Calendar.YEAR)-2000+"";
        String  day = String.format("%1$03d",calendar.get(Calendar.DAY_OF_YEAR));
        String hour = String.format("%1$02d",calendar.get(Calendar.HOUR_OF_DAY));
        return year+day+hour;
    }

    /**
     * 订单后缀生成
     * @return
     */
    private String genOrderNoSuf(){
        redisTemplate.setKeySerializer(new StringRedisSerializer());

        //设置自增计数器 1小时失效
        if(redisTemplate.opsForValue().get(ORDER_NUM_KEY) == null){
           redisTemplate.opsForValue().set(ORDER_NUM_KEY,"0",1,TimeUnit.HOURS);
        }

        long no = redisTemplate.opsForValue().increment(ORDER_NUM_KEY,1);
        return String.format("%1$05d",no);
    }

    /**
     * 订单号生成
     * @return
     */
    private String genOrderNo(){
        return shopId+genOrderNoPre()+genOrderNoSuf();
    }

    /**
     * 创建子订单
     * @param orderNo
     * @param cartItems
     * @return
     */
    private List<OrderItem> createOrderItem(String orderNo,List<CartItem> cartItems){
        List<OrderItem> list =
            cartItems.stream().map(x -> {
                OrderItem orderItem = new OrderItem();
                orderItem.setId(ObjectId.genGUID());
                orderItem.setOrderNo(orderNo);
                orderItem.setSpu(x.getProduct().getSpu());
                orderItem.setName(x.getProduct().getName());
                orderItem.setThumbnail(x.getProduct().getThumbnail());
                orderItem.setQuantity(x.getQuantity());
                orderItem.setTotal(x.getProduct().getSalePrice().multiply(new BigDecimal(x.getQuantity())));
                return orderItem;
            }).collect(Collectors.toList());

        //库存扣减
        reduceStock(list);
        orderItemRepo.saveItemList(list);
        return list;
    }

    /**
     * 创建订单
     * @param customerId
     * @param cartItems
     * @return
     */
    private Order createOrder(String customerId,List<CartItem> cartItems){
        //创建订单号
        String orderNo = genOrderNo();

        //创建子订单项
        List<OrderItem> itemList = createOrderItem(orderNo,cartItems);
        BigDecimal total = itemList.stream().map(x -> x.getTotal()).reduce(new BigDecimal(0),BigDecimal::add);

        //创建主订单
        Order order = new Order();
        order.setNumber(orderNo);
        order.setCustomerId(customerId);
        order.setStatus(OrderState.NEW);
        order.setTotal(total);
        orderRepo.saveOrder(order);
        return order;
    }

    /**
     * 扣减库存
     * @param orderItems
     */
    private void reduceStock(List<OrderItem> orderItems){
        int result = stockRepo.updateStock(orderItems);
        if(orderItems.size()!=result){
            throw new ServiceException("扣减库存失败");
        }
    }

    /**
     * 获取我的订单
     * @param customerId
     * @return
     */
    //public Result getMyOrder(String customerId){
      //  orderRepo.getOrderByCustomerId(customerId);
    //}



}
