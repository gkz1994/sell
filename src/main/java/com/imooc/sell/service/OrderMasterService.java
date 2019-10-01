package com.imooc.sell.service;

import com.imooc.sell.dto.OrderMasterDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * 订单主表service层
 */
public interface OrderMasterService {

    /**创建订单*/
    OrderMasterDto create(OrderMasterDto OrderMasterDto);

    /**查找单个订单*/
    OrderMasterDto findOne(String OrderMasterId);

    /**查找订单列表*/
    List<OrderMasterDto>findList(String buyerOpenId, Pageable pageable);

    /**取消订单*/
    OrderMasterDto cancel(OrderMasterDto orderMasterDto);

    /**支付订单*/
    OrderMasterDto paid(OrderMasterDto orderMasterDto);

    /**完结订单*/
    OrderMasterDto finish(OrderMasterDto orderMasterDto);
}
