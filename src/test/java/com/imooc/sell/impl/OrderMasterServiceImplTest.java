package com.imooc.sell.impl;

import com.imooc.sell.dto.OrderMasterDto;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.utils.KeyUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void paid() {
    }

    @Test
    public void finish() {
    }
}