package com.imooc.sell.impl;

import com.imooc.sell.Enums.ProductInfoStatusEnum;
import com.imooc.sell.dao.ProductInfoDao;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ProductInfoServiceImpl
 * @Description TODO
 * @Author gkz
 * @Date 2019/9/26 18:07
 * @Version 1.0
 **/
@Service
public class ProductInfoServiceImpl implements ProductInfoService {

    @Autowired
    private ProductInfoDao dao;

    @Override
    public ProductInfo findOne(String productId) {
        return dao.findOne(productId);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return dao.findByProductStatus(ProductInfoStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return dao.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return dao.save(productInfo);
    }
}
