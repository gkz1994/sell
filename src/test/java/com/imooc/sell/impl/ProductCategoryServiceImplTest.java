package com.imooc.sell.impl;

import com.imooc.sell.entity.ProductCategory;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductCategoryServiceImplTest {

    @Autowired
    private ProductCategoryServiceImpl categoryService;

    @Test
    public void findOne() {
        ProductCategory category = categoryService.findOne(5);
        Assert.assertNotNull(category.toString());
    }

    @Test
    public void findAll() {
        List<ProductCategory> categoryList = categoryService.findAll();
        System.out.println(categoryList);
    }

    @Test
    public void findCategoryByCategoryTypeIn() {
        List<ProductCategory> category = categoryService.findCategoryByCategoryTypeIn(Arrays.asList(1, 2, 3, 4));
        Assert.assertNotEquals(0,category.size());

    }

    @Test
    public void save() {
        ProductCategory productCategory=new ProductCategory("咖啡",6);
        ProductCategory productCategory1=categoryService.save(productCategory);
        Assert.assertNotNull(productCategory);
    }
}