package com.coffee.shop.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 客户
 *
 * @author waylon
 * @date 2018/07/30
 **/
@Getter
@Setter
@NoArgsConstructor
public class Customer extends Guest{

    private String name;

    private String phone;

    private String password;

    private String gender;

}
