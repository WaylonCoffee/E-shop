package com.coffee.shop.service;

import com.coffee.shop.component.message.Result;
import com.coffee.shop.component.message.ResultGenerator;
import com.coffee.shop.domain.CategoryRepository;
import com.coffee.shop.domain.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 商品服务
 * @author waylon
 * @date 2018/07/31
 **/
@Service
public class CatalogService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;

    /**
     * 获取所有商品
     * @return
     */
    public Result getAllProducts(){
        return ResultGenerator.genSuccessResult(productRepo.getAllProducts());
    }

    /**
     * 获取分类商品
     * @param categoryId
     * @return
     */
    public Result getProductsByCategoryId(String categoryId){
        return ResultGenerator.genSuccessResult(productRepo.getProductsByCategoryId(categoryId));
    }

    /**
     * 模糊搜索商品
     * @param name
     * @return
     */
    public Result searchProductsByName(String name){
        return ResultGenerator.genSuccessResult(productRepo.searchProductByName(name));
    }

    /**
     * 查看商品详情
     * @param spu
     * @return
     */
    public Result viewProduct(String spu){
        return ResultGenerator.genSuccessResult(productRepo.getProductBySpu(spu));
    }

    /**
     * 查看商品详情
     * @return
     */
    public Result getAllCategories(){
        return ResultGenerator.genSuccessResult(categoryRepo.getAllCategories());
    }

}
