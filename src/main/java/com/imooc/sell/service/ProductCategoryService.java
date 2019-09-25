package com.imooc.sell.service;

import com.imooc.sell.entity.ProductCategory;

import java.util.List;

/**
 * 商品类目service
 */
public interface ProductCategoryService {

    //根据商品id查找商品
    ProductCategory findOne(int categoryId);

    //查找所有的商品
    List<ProductCategory> findAll();

    //根据categoryType查找所有商品
    List<ProductCategory>findCatgeoryByCategoryTypeIn(List<Integer>categoryType);

    //保存
    ProductCategory save(ProductCategory productCategory);
}
