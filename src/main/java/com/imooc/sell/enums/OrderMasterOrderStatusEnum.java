package com.imooc.sell.enums;

import lombok.Getter;

/**
 * @ClassName OrderMasterStatusEnum
 * @Description 订单状态枚举类
 * @Author gkz
 * @Date 2019/9/29 13:20
 * @Version 1.0
 **/
@Getter
public enum  OrderMasterOrderStatusEnum {

    NEW(0,"新订单"),
    FINISHED(1,"完结"),
    CANCEL(2,"取消")
    ;

    private Integer code;

    private String message;

    OrderMasterOrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
