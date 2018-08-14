package com.coffee.shop.web;

import com.coffee.shop.component.message.Result;
import com.coffee.shop.service.CartService;
import com.coffee.shop.service.CatalogService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 商品控制器
 *
 * @author waylon
 * @date 2018/08/01
 **/
@RestController()
@RequestMapping("/catalog")
@PropertySource("classpath:business.properties")
public class CatalogController {

    @Autowired
    private CatalogService catalogService;

    @Autowired
    private CartService cartService;

    @Value("${page.size}")
    private Integer pageSize;




    /**
     * 商品列表
     * @param pageNum
     * @return
     */
    @GetMapping("/products")
    public Result getAllProducts(@RequestParam(defaultValue = "1") Integer pageNum){
        //cartService.getCartByCustomer("1");
        System.out.println(cartService.viewCart("1"));
        PageHelper.startPage(pageNum,pageSize);

        return catalogService.getAllProducts();
    }

    /**
     * 商品搜索
     * @param name
     * @param pageNum
     * @return
     */
    @GetMapping("/products/search")
    public Result searchProductsByName(@RequestParam String name, @RequestParam(defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        return catalogService.searchProductsByName(name);
    }

    /**
     * 分类搜索
     * @param categoryId
     * @param pageNum
     * @return
     */
    @GetMapping("/categories/{categoryId}")
    public Result getProductsByCategoryId(@PathVariable String categoryId, @RequestParam(defaultValue = "1") Integer pageNum){
        PageHelper.startPage(pageNum,pageSize);
        return catalogService.getProductsByCategoryId(categoryId);
    }

    /**
     * 获取商品详情
     * @param spu
     * @return
     */
    @GetMapping("/products/{spu}")
    public Result viewProduct(@PathVariable String spu){
        return catalogService.viewProduct(spu);
    }

    /**
     * 获取分类列表
     * @return
     */
    @GetMapping("/categories")
    public Result getCategories(){
        return catalogService.getAllCategories();
    }



}
