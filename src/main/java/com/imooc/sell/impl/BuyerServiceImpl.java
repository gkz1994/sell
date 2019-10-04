package com.imooc.sell.impl;

import com.imooc.sell.dto.OrderMasterDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderMasterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName BuyerServiceImpl
 * @Description TODO
 * @Author gkz
 * @Date 2019/10/5 02:11
 * @Version 1.0
 **/
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {

    @Autowired
    private OrderMasterService orderMasterService;

    @Override
    public OrderMasterDto findOrderOne(String openId, String orderId) {
        return checkOrderOwner(openId, orderId);
    }

    @Override
    public OrderMasterDto cancelOrder(String openId, String orderId) {
        OrderMasterDto orderOwner = checkOrderOwner(openId, orderId);
        if(orderOwner==null){
            log.error("【取消订单】查不到该订单,orderId={}",orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        return orderMasterService.cancel(orderOwner);
    }

    private OrderMasterDto checkOrderOwner(String openId, String orderId){
        OrderMasterDto masterDto = orderMasterService.findOne(orderId);
        if(masterDto==null){
            return null;
        }
        //判断是否是自己的订单
        if(!masterDto.getBuyerOpenid().equalsIgnoreCase(openId)){
            log.error("【查询订单】订单的openid不一致.openid={},orderMasterDto={}",openId,masterDto);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return masterDto;
    }
}
