package com.coffee.shop.service;

import com.coffee.shop.component.message.Result;
import com.coffee.shop.component.message.ResultGenerator;
import com.coffee.shop.domain.Customer;
import com.coffee.shop.domain.CustomerRepository;
import com.coffee.shop.domain.Guest;
import com.coffee.shop.domain.GuestRepository;
import com.coffee.shop.dto.CustomerForm;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 客户服务
 *
 * @author waylon
 * @date 2018/07/30
 **/
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepo;

    @Autowired
    private GuestRepository guestRepo;


    /**
     * 注册用户
     * @param customerForm
     * @return
     */
    public Result register(CustomerForm customerForm){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customer,customer);
        return ResultGenerator.genSuccessResult(customer);
    }






}
