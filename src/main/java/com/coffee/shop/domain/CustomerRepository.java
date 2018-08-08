package com.coffee.shop.domain;

import org.springframework.stereotype.Repository;

/**
 * 客户repo
 * @author waylon
 * @date 2018/07/30
 **/
@Repository
public interface CustomerRepository {

    /**
     * 保存用户
     * @param customer
     */
    public void save(Customer customer);

}
