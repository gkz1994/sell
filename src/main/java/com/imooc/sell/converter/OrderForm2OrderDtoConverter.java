package com.imooc.sell.converter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.imooc.sell.dto.OrderMasterDto;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderForm2OrderDtoConverter
 * @Description TODO
 * @Author gkz
 * @Date 2019/10/4 01:59
 * @Version 1.0
 **/
@Slf4j
public class OrderForm2OrderDtoConverter {

    public static OrderMasterDto convert(OrderForm orderForm){

        Gson gson=new Gson();

        OrderMasterDto orderMasterDto=new OrderMasterDto();
        orderMasterDto.setBuyerName(orderForm.getName());
        orderMasterDto.setBuyerPhone(orderForm.getPhone());
        orderMasterDto.setBuyerOpenid(orderForm.getOpenId());
        orderMasterDto.setBuyerAddress(orderForm.getAddress());
        List<OrderDetail>orderDetails=new ArrayList<>();
        try {
            orderDetails=gson.fromJson(orderForm.getItems(),new TypeToken<List<OrderDetail>>(){}.getType());
        }catch (Exception e){
            log.error("【对象转换】错误,String={}",orderForm.getItems());
            throw new SellException(ResultEnum.PARAM_ERROR);
        }

        orderMasterDto.setOrderDetailList(orderDetails);
        return orderMasterDto;
    }
}
