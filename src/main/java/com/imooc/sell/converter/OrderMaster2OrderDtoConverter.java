package com.imooc.sell.converter;

import com.imooc.sell.dto.OrderMasterDto;
import com.imooc.sell.entity.OrderMaster;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OrderMaster2OrderDtoConverter
 * @Description TODO
 * @Author gkz
 * @Date 2019/10/3 18:10
 * @Version 1.0
 **/
public class OrderMaster2OrderDtoConverter {

    public static OrderMasterDto convert(OrderMaster orderMaster){
        OrderMasterDto orderMasterDto=new OrderMasterDto();
        BeanUtils.copyProperties(orderMaster,orderMasterDto);
        return orderMasterDto;
    }

    public static List<OrderMasterDto>convert(List<OrderMaster>orderMasters){
        return orderMasters.stream().map(e->convert(e)).collect(Collectors.toList());
    }
}
