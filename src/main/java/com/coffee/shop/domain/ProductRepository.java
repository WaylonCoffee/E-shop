package com.coffee.shop.domain;

import com.coffee.shop.domain.enums.ProductState;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品repo
 *
 * @author waylon
 * @date 2018/07/31
 **/
@Repository
public interface ProductRepository {

    /**
     * 获取所有商品列表
     * @return
     */
    @Select("SELECT spu,name,sale_price,market_price,category,thumbnail,detail,create_date FROM product WHERE status = 'ON'")
    List<Product> getAllProducts();

    /**
     * 根据分类id获取商品列表
     * @param categoryId
     * @return
     */
    @Select("SELECT spu,name,sale_price,market_price,category,thumbnail,detail,create_date FROM product WHERE status = 'ON' and category = #{categoryId}")
    List<Product> getProductsByCategoryId(@Param("categoryId") String categoryId);

    /**
     * 根据spu查看商品
     * @return
     */
    @Select("SELECT spu,name,sale_price,market_price,category,thumbnail,detail,create_date FROM product WHERE spu = #{spu} and status = 'ON'")
    @Results({
            @Result(column = "spu", property = "imageList",
                    many = @Many(
                            select = "com.coffee.shop.domain.ProductImageRepository.getProductImageListBySpu",
                            fetchType = FetchType.EAGER
                    )
            )
    })
    Product getProductBySpu(@Param("spu")String spu);


    /**
     * 查找图片
     * @param spu
     * @return
     */
    @Select("SELECT spu,name,sale_price,market_price,category,thumbnail,detail,create_date FROM product WHERE spu = #{spu} and status = 'ON'")
    Product getProductBySpuWithoutImages(@Param("spu")String spu);

    /**
     * 模糊搜索商品
     * @param name
     * @return
     */
    @Select("SELECT spu,name,sale_price,market_price,category,thumbnail,detail,create_date FROM product WHERE status = 'ON' and name like CONCAT(CONCAT('%', #{name}),'%')")
    List<Product> searchProductByName(@Param("name") String name);

}
