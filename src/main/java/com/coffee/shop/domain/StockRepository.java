package com.coffee.shop.domain;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 库存repo
 * @author waylon
 * @date 2018/08/08
 **/
@Repository
public interface StockRepository {

    /**
     * 查询库存
     * @param spu
     * @return
     */
    @Select("SELECT spu,num FROM stock WHERE spu = #{spu}")
    Stock getStockBySpu(String spu);

    @Select("SELECT spu,num FROM stock WHERE spu IN(${spus})")
    List<Stock> getStockList(@Param("spus") String spus);

}
