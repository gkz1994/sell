package com.imooc.sell.impl;

import com.imooc.sell.enums.ProductInfoStatusEnum;
import com.imooc.sell.entity.ProductInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductInfoServiceImplTest {

    @Autowired
    private ProductInfoServiceImpl productInfoService;

    @Test
    public void findOne() {
        ProductInfo info = productInfoService.findOne("11");
        Assert.assertNotEquals(null,info.getProductId());
    }

    @Test
    public void findUpAll() {
        List<ProductInfo> infoList = productInfoService.findUpAll();
        Assert.assertNotEquals(0,infoList.size());
    }

    @Test
    public void findAll() {
        PageRequest request=new PageRequest(0,2);
        Page<ProductInfo> page = productInfoService.findAll(request);
        Assert.assertNotEquals(0,page.getTotalElements());
    }

    @Test
    public void save() {
        ProductInfo productInfo=new ProductInfo();
        productInfo.setProductId("13");
        productInfo.setProductName("美式黑咖啡 Americano");
        productInfo.setProductPrice(new BigDecimal(23));
        productInfo.setProductStock(100);
        productInfo.setProductDescription("350毫升|=双份浓缩咖啡+热水");
        productInfo.setProductIcon("http://www.baidu.com");
        productInfo.setProductStatus(ProductInfoStatusEnum.UP.getCode());
        productInfo.setCategoryType(3);
        productInfoService.save(productInfo);
    }
}