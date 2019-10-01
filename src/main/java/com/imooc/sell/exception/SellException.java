package com.imooc.sell.exception;

import com.imooc.sell.enums.ResultEnum;

/**
 * @ClassName SellException
 * @Description 商品不存在异常类
 * @Author gkz
 * @Date 2019/10/1 20:27
 * @Version 1.0
 **/
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }
}
