package com.imooc.sell.controller;

import com.imooc.sell.converter.OrderForm2OrderDtoConverter;
import com.imooc.sell.dto.OrderMasterDto;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.form.OrderForm;
import com.imooc.sell.service.BuyerService;
import com.imooc.sell.service.OrderMasterService;
import com.imooc.sell.utils.ResultVoUtils;
import com.imooc.sell.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName BuyerOrderController
 * @Description 买家订单控制层
 * @Author gkz
 * @Date 2019/10/4 01:50
 * @Version 1.0
 **/
@RestController
@RequestMapping("/buyer/order")
@Slf4j
public class BuyerOrderController {

    @Autowired
    private OrderMasterService orderMasterService;

    @Autowired
    private BuyerService buyerService;

    //创建订单
    @PostMapping("/create")
    public ResultVO<Map<String,String>>create(@Valid OrderForm orderForm, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            log.error("【创建订单】参数不正确,orderForm={}",orderForm);
            throw new SellException(ResultEnum.PARAM_ERROR.getCode(),bindingResult.getFieldError().getDefaultMessage());
        }
        OrderMasterDto orderMasterDto= OrderForm2OrderDtoConverter.convert(orderForm);
        if(CollectionUtils.isEmpty(orderMasterDto.getOrderDetailList())){
            log.error("【创建订单】购物车不能为空");
            throw new SellException(ResultEnum.CART_EMPTY);
        }
        OrderMasterDto masterDto = orderMasterService.create(orderMasterDto);
        Map<String,String>map=new HashMap<>();
        map.put("orderId",masterDto.getOrderId());
        return ResultVoUtils.success(map);
    }

    //订单列表
    @GetMapping("/list")
    public ResultVO<List<OrderMasterDto>>list(@RequestParam("openId")String openId,
                                              @RequestParam(value="page",defaultValue = "0")Integer page,
                                              @RequestParam(value="size",defaultValue = "10")Integer size){
        if(StringUtils.isEmpty(openId)){
            log.error("【查询订单列表】openid为空");
            throw new SellException(ResultEnum.PARAM_ERROR);
        }
        PageRequest request=new PageRequest(page,size);
        Page<OrderMasterDto> dtoPage = orderMasterService.findList(openId, request);

        return ResultVoUtils.success(dtoPage.getContent());
    }

    //订单详情
    @GetMapping("/detail")
    public ResultVO<List<OrderMasterDto>>detail(@RequestParam("openId")String openId,
                                                @RequestParam("orderId")String orderId){
        OrderMasterDto orderOne = buyerService.findOrderOne(openId, orderId);
        return  ResultVoUtils.success(orderOne);
    }

    //取消订单
    @PostMapping("/cancel")
    public ResultVO cancel(@RequestParam("openId")String openId,
                           @RequestParam("orderId")String orderId){
        buyerService.cancelOrder(openId, orderId);
        return ResultVoUtils.success();
    }
}
