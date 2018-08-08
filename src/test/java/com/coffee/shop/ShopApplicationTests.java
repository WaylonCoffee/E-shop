package com.coffee.shop;

import com.coffee.shop.domain.CartRepository;
import com.coffee.shop.domain.ProductRepository;
import com.coffee.shop.domain.StockRepository;
import com.coffee.shop.service.CartService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopApplicationTests {

    @Autowired
    private CartService cartService;

    @Test
    public void contextLoads() {
    }

    @Test
    public void a(){
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        System.out.println(cartService.checkout(list,"1"));
    }

}
