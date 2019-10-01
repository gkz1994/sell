package com.imooc.sell.dto;

import lombok.Data;

/**
 * @ClassName CartDto
 * @Description 购物车dto类
 * @Author gkz
 * @Date 2019/10/1 21:17
 * @Version 1.0
 **/
@Data
public class CartDto {

    /**商品id*/
    private String productId;

    /**购买的数量*/
    private Integer productQuantity;

    public CartDto(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
