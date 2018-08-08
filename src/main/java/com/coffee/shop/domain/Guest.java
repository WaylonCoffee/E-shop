package com.coffee.shop.domain;

import com.coffee.shop.dto.CustomerForm;
import com.fasterxml.jackson.databind.util.BeanUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

/**
 * 游客
 *
 * @author waylon
 * @date 2018/07/30
 **/
@Getter
@Setter
@NoArgsConstructor
public class Guest {


    private String openId;


    /**
     * 注册用户
     * @param form
     * @return
     */
    public Customer register(CustomerForm form){
        Customer customer = new Customer();
        BeanUtils.copyProperties(form,customer);
        return customer;
    }


}
