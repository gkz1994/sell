package com.imooc.sell.dao;

import com.imooc.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProductCategoryDaoTest {

    @Autowired
    private ProductCategoryDao dao;

    //根据categoryId查找categroy对象
    @Test
    public void findOne(){
        ProductCategory category = dao.findOne(1);
        System.out.println(category.toString());
    }

    //保存
    @Test
    public void save(){
        ProductCategory productCategory=new ProductCategory("手冲咖啡",5);
        ProductCategory category=dao.save(productCategory);
        Assert.assertNotNull(category);
    }

    //测试查询全部的
    @Test
    public void findProductCategoryByCategoryType(){
        List<Integer>categoryTypes= Arrays.asList(2,3,4);
        List<ProductCategory> categories = dao.findProductCategoryByCategoryTypeIn(categoryTypes);
        Assert.assertNotEquals(null,categories.size());
    }
}