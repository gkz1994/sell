package com.imooc.sell.impl;

import com.imooc.sell.dto.CartDto;
import com.imooc.sell.enums.ProductInfoStatusEnum;
import com.imooc.sell.dao.ProductInfoDao;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.ProductInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public void increaseStock(List<CartDto> cartDto) {
        for (CartDto cart:cartDto) {
            ProductInfo productInfo=dao.findOne(cart.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //增加库存
            Integer stock=productInfo.getProductStock()+cart.getProductQuantity();
            productInfo.setProductStock(stock);
            dao.save(productInfo);
        }
    }

    @Override
    @Transactional
    public void decreaseStock(List<CartDto> cartDto) {
        for (CartDto cartdto:cartDto) {
            ProductInfo productInfo = dao.findOne(cartdto.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            Integer stock=productInfo.getProductStock()-cartdto.getProductQuantity();
            if(stock<0){
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            productInfo.setProductStock(stock);
            dao.save(productInfo);
        }
    }
}
