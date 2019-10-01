package com.imooc.sell.service;

import com.imooc.sell.dto.CartDto;
import com.imooc.sell.entity.ProductInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductInfoService {

    ProductInfo findOne(String productId);

    /**
     *
     查询所有在架的商品
     */
    List<ProductInfo> findUpAll();

    Page<ProductInfo>findAll(Pageable pageable);

    ProductInfo save(ProductInfo productInfo);

    //加库存
    void increaseStock(List<CartDto> cartDto);
    //减库存
    void decreaseStock(List<CartDto> cartDto);
}
