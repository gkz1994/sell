package com.imooc.sell.vo;

import lombok.Data;

/**
 * @ClassName ResultVO
 * @Description 返回给前端的最外层
 * @Author gkz
 * @Date 2019/9/27 19:15
 * @Version 1.0
 **/
@Data
public class ResultVO<T> {

    /**错误码*/
    private Integer code;

    private String msg;

    private T data;
}
