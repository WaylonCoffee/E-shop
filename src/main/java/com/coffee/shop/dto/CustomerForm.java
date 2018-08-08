package com.coffee.shop.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 客户注册表单
 *
 * @author waylon
 * @date 2018/07/30
 **/
@Setter
@Getter
@NoArgsConstructor
public class CustomerForm {

    private String openId;

    private String name;

    private String phone;

    private String password;

    private String gender;

}
