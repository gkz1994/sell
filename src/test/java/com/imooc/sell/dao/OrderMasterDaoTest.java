package com.imooc.sell.dao;

import com.imooc.sell.entity.OrderMaster;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderMasterDaoTest {

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Test
    public void saveTest(){
        OrderMaster master=new OrderMaster();
        master.setOrderId("1");
        master.setBuyerName("谷康章");
        master.setBuyerPhone("15990730371");
        master.setBuyerAddress("水心汇昌");
        master.setBuyerOpenid("111");
        master.setOrderAmount(new BigDecimal(23));
        OrderMaster orderMaster = orderMasterDao.save(master);
        Assert.assertNotNull(orderMaster);
    }

    @Test
    public void findByBuyerOpenId() {
        PageRequest request=new PageRequest(0,2);
        Page<OrderMaster> masters = orderMasterDao.findByBuyerOpenid("111", request);
        Assert.assertNotEquals(0,masters.getTotalElements());

    }
}