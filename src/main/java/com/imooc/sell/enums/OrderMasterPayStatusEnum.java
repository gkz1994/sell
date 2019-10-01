package com.imooc.sell.enums;

import lombok.Getter;

/**
 * 订单支付枚举
 */
@Getter
public enum OrderMasterPayStatusEnum {

    WAIT(0,"等待支付"),

    SUCCESS(1,"支付成功")
    ;

    private Integer code;

    private String message;

    OrderMasterPayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
