package com.imooc.sell.service;

import com.imooc.sell.dto.OrderMasterDto;

/**
 * 买家端service
 */
public interface BuyerService {

    //查询一个订单
   OrderMasterDto findOrderOne(String openId,String orderId);

   //取消订单
   OrderMasterDto cancelOrder(String openId,String orderId);
}
