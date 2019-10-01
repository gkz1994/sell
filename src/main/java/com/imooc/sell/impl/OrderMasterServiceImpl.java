package com.imooc.sell.impl;

import com.imooc.sell.dao.OrderDetailDao;
import com.imooc.sell.dao.OrderMasterDao;
import com.imooc.sell.dto.CartDto;
import com.imooc.sell.dto.OrderMasterDto;
import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.entity.OrderMaster;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.enums.OrderMasterOrderStatusEnum;
import com.imooc.sell.enums.OrderMasterPayStatusEnum;
import com.imooc.sell.enums.ResultEnum;
import com.imooc.sell.exception.SellException;
import com.imooc.sell.service.OrderMasterService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.utils.KeyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName OrderMasterServiceImpl
 * @Description TODO
 * @Author gkz
 * @Date 2019/10/1 20:19
 * @Version 1.0
 **/
@Service
public class OrderMasterServiceImpl implements OrderMasterService {

    String orderId=KeyUtils.genUniqueKey();

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    @Transactional
    public OrderMasterDto create(OrderMasterDto OrderMasterDto) {

        BigDecimal amount=new BigDecimal(BigInteger.ZERO);

        String orderId=KeyUtils.genUniqueKey();

        List<CartDto>cartDtos=new ArrayList<>();

        //1.查询商品.价格
        for(OrderDetail orderDetail:OrderMasterDto.getOrderDetailList()){
            ProductInfo productInfo = productInfoService.findOne(orderDetail.getProductId());
            if(productInfo==null){
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //2.计算总价
            amount=productInfo.getProductPrice().multiply(new BigDecimal(orderDetail.getProductQuantity())).add(amount);
            orderDetail.setDetailId(KeyUtils.genUniqueKey());
            orderDetail.setOrderId(orderId);
            BeanUtils.copyProperties(productInfo,orderDetail);
            orderDetailDao.save(orderDetail);
            CartDto cartDto=new CartDto(orderDetail.getProductId(),orderDetail.getProductQuantity());
            cartDtos.add(cartDto);
        };
        //3.订单入库
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(OrderMasterDto,orderMaster);
        orderMaster.setOrderId(orderId);
        orderMaster.setOrderAmount(amount);
        orderMaster.setOrderStatus(OrderMasterOrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(OrderMasterPayStatusEnum.WAIT.getCode());
        orderMasterDao.save(orderMaster);
        //4.扣库存
        productInfoService.decreaseStock(cartDtos);
        return OrderMasterDto;
    }

    @Override
    public OrderMasterDto findOne(String OrderMasterId) {
        return null;
    }

    @Override
    public List<OrderMasterDto> findList(String buyerOpenId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderMasterDto cancel(OrderMasterDto orderMasterDto) {
        return null;
    }

    @Override
    public OrderMasterDto paid(OrderMasterDto orderMasterDto) {
        return null;
    }

    @Override
    public OrderMasterDto finish(OrderMasterDto orderMasterDto) {
        return null;
    }
}
