package com.coffee.shop.domain;

import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 商品图片
 * @author waylon
 * @date 2018/08/01
 **/
@Repository
public interface ProductImageRepository {
    /**
     * 获取商品图片
     * @param spu
     * @return
     */
    @Select("SELECT path FROM image WHERE spu = #{spu}")
    List<ProductImage> getProductImageListBySpu(String spu);
}
