package com.imooc.sell.impl;

import com.imooc.sell.dto.OrderMasterDto;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.enums.OrderMasterOrderStatusEnum;
import com.imooc.sell.enums.OrderMasterPayStatusEnum;
import com.imooc.sell.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderMasterServiceImplTest {

    public static final String BUYER_OPEN_ID="10010";

    @Autowired
    private OrderMasterServiceImpl orderMasterService;

    @Test
    public void create() {
        OrderMasterDto orderMasterDto=new OrderMasterDto();
        orderMasterDto.setBuyerName("gkz");
        orderMasterDto.setBuyerAddress("水心汇昌");
        orderMasterDto.setBuyerOpenid(BUYER_OPEN_ID);
        orderMasterDto.setBuyerPhone("15990730371");
        List<OrderDetail>orderDetail=new ArrayList<>();
        OrderDetail orderDetail1=new OrderDetail();
        orderDetail1.setProductId("12");
        orderDetail1.setProductQuantity(3);
        orderDetail.add(orderDetail1);
        orderMasterDto.setOrderDetailList(orderDetail);
        OrderMasterDto dto = orderMasterService.create(orderMasterDto);
        log.info("【创建订单】dto={}",dto);
    }

    @Test
    public void findOne() {
        OrderMasterDto one = orderMasterService.findOne("1569944005548269003");
        log.info("【查询单个订单】one:{}",one);
        Assert.assertNotEquals(0,one.getOrderId());
    }

    @Test
    public void findList() {
        PageRequest request=new PageRequest(0,2);
        Page<OrderMasterDto> page = orderMasterService.findList("10010", request);
        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderMasterDto one = orderMasterService.findOne("1569944005548269003");
        OrderMasterDto orderMasterDto = orderMasterService.cancel(one);
        Assert.assertEquals(OrderMasterOrderStatusEnum.CANCEL.getCode(),orderMasterDto.getOrderStatus());
    }

    @Test
    public void finish() {
        OrderMasterDto one = orderMasterService.findOne("1569944005548269003");
        OrderMasterDto finish = orderMasterService.finish(one);
        Assert.assertEquals(OrderMasterOrderStatusEnum.FINISHED.getCode(),finish.getOrderStatus());
    }

    @Test
    public void paid() {
        OrderMasterDto one = orderMasterService.findOne("1569944005548269003");
        OrderMasterDto finish = orderMasterService.paid(one);
        Assert.assertEquals(OrderMasterPayStatusEnum.SUCCESS.getCode(),finish.getPayStatus());
    }
}