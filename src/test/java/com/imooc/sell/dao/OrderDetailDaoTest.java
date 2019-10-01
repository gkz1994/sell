package com.imooc.sell.dao;

import com.imooc.sell.entity.OrderDetail;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailDaoTest {

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Test
    public void findByOrderId() {
        List<OrderDetail> id = orderDetailDao.findByOrderId("1");
        Assert.assertNotEquals(0,id.size());
    }

    @Test
    public void save(){
        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setDetailId("1");
        orderDetail.setOrderId("1");
        orderDetail.setProductIcon("http://wwww.baidu.com");
        orderDetail.setProductId("11");
        orderDetail.setProductName("耶加雪菲、杜梅索G1、水洗");
        orderDetail.setProductPrice(new BigDecimal(30));
        orderDetail.setProductQuantity(100);
        OrderDetail detail = orderDetailDao.save(orderDetail);
        Assert.assertNotNull(detail);
    }
}