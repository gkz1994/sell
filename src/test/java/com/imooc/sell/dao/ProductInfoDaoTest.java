package com.imooc.sell.dao;

import com.imooc.sell.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoDaoTest {

    @Autowired
    private ProductInfoDao dao;

    @Test
    public void findByStatus() {
        List<ProductInfo> infoList = dao.findByProductStatus(0);
        Assert.assertNotEquals(0,infoList.size());
    }

    @Test
    public void save(){
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("12");
        productInfo.setProductName("古吉、草莓红颜、日晒");
        productInfo.setProductPrice(new BigDecimal(30));
        productInfo.setProductStock(999);
        productInfo.setProductDescription("草莓、蜜桃、奶油香气");
        productInfo.setProductIcon("http://www.baidu.com");
        productInfo.setProductStatus(0);
        productInfo.setCategoryType(2);
        dao.save(productInfo);
    }

}