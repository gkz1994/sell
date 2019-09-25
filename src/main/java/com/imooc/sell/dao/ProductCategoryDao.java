package com.imooc.sell.dao;

import com.imooc.sell.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @ClassName ProductCategoryDao
 * @Description 商品类目dao层接口
 * @Author gkz
 * @Date 2019/9/25 20:33
 * @Version 1.0
 **/
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory>findProductCategoryByCategoryTypeIn(List<Integer>categoryType);
}
