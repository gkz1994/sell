package com.imooc.sell.Enums;

import lombok.Getter;

/**
 * 商品上下架的枚举类
 */
@Getter
public enum ProductInfoStatusEnum {

    UP(0,"在架"),
    DOWN(1,"下架")
    ;

    private Integer code;

    private String message;

    ProductInfoStatusEnum(Integer code,String message) {
        this.code=code;
        this.message=message;
    }
}
