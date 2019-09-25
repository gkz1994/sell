package com.imooc.sell.impl;

import com.imooc.sell.dao.ProductCategoryDao;
import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName ProductCategoryServiceImpl
 * @Description 商品类目service实现类
 * @Author gkz
 * @Date 2019/9/25 21:04
 * @Version 1.0
 **/
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryDao;

    @Override
    public ProductCategory findOne(int categoryId) {
        return productCategoryDao.findOne(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryDao.findAll();
    }

    @Override
    public List<ProductCategory> findCatgeoryByCategoryTypeIn(List<Integer> categoryType) {
        return productCategoryDao.findProductCategoryByCategoryTypeIn(Arrays.asList(2,3,4));
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryDao.save(productCategory);
    }
}
