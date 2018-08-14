package com.coffee.shop;

import com.coffee.shop.domain.CartRepository;
import com.coffee.shop.domain.OrderRepository;
import com.coffee.shop.domain.ProductRepository;
import com.coffee.shop.domain.StockRepository;
import com.coffee.shop.service.CartService;
import com.coffee.shop.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Thread.sleep;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopApplicationTests {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void a()throws Exception{


        System.out.println(orderRepository.getOrderByCustomerId("1"));

    }

}
