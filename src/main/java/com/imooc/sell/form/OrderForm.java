package com.imooc.sell.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @ClassName OrderForm
 * @Description 用于表单验证的类
 * @Author gkz
 * @Date 2019/10/4 01:46
 * @Version 1.0
 **/
@Data
public class OrderForm {

    /**买家姓名*/
    @NotEmpty(message="姓名必填")
    private String name;

    /**买家手机号*/
    @NotEmpty(message="手机号必填")
    private String phone;

    /**买家手机号*/
    @NotEmpty(message="地址必填")
    private String address;

    /**买家微信openId*/
    @NotEmpty(message="openId必填")
    private String openId;

    /**购物车*/
    @NotEmpty(message="购物车不能为空")
    private String items;
}
