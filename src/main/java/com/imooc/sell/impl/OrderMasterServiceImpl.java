package com.imooc.sell.impl;

import com.imooc.sell.converter.OrderMaster2OrderDtoConverter;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName OrderMasterServiceImpl
 * @Description TODO
 * @Author gkz
 * @Date 2019/10/1 20:19
 * @Version 1.0
 **/
@Service
@Slf4j
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
        OrderMasterDto.setOrderId(orderId);
        BeanUtils.copyProperties(OrderMasterDto,orderMaster);
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
        OrderMaster orderMaster = orderMasterDao.findOne(OrderMasterId);
        if(orderMaster==null){
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }
        List<OrderDetail> detailList = orderDetailDao.findByOrderId(OrderMasterId);
        if(CollectionUtils.isEmpty(detailList)){
            throw new SellException(ResultEnum.ORDERDETAIL_NOT_EXIST);
        }
        OrderMasterDto orderMasterDto=new OrderMasterDto();
        BeanUtils.copyProperties(orderMaster,orderMasterDto);
        orderMasterDto.setOrderDetailList(detailList);
        return orderMasterDto;
    }

    @Override
    public Page<OrderMasterDto> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenId, pageable);

        List<OrderMasterDto> orderDTOList = OrderMaster2OrderDtoConverter.convert(orderMasterPage.getContent());

        return new PageImpl<OrderMasterDto>(orderDTOList, pageable, orderMasterPage.getTotalElements());
    }

    @Override
    @Transactional
    public OrderMasterDto cancel(OrderMasterDto orderMasterDto) {
       OrderMaster orderMaster=new OrderMaster();
        //判断订单状态
        if(!orderMasterDto.getOrderStatus().equals(OrderMasterOrderStatusEnum.NEW.getCode())){
            log.info("【取消订单】订单状态不正确，orderId={},orderStatus={}",orderMasterDto.getOrderId(),orderMasterDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMasterDto.setOrderStatus(OrderMasterOrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderMasterDto,orderMaster);
        OrderMaster updateResult = orderMasterDao.save(orderMaster);
        if(updateResult==null){
            log.info("【取消订单】更新失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        //返回库存
        if(CollectionUtils.isEmpty(orderMasterDto.getOrderDetailList())){
            log.info("【取消订单】订单中无商品详情，orderDto={}",orderMasterDto);
            throw new SellException(ResultEnum.ORDER_DETAIL_EMPTY);
        }
        List<CartDto>cartDtos=orderMasterDto.getOrderDetailList().
                stream().
                map(e->new CartDto(e.getProductId(),e.getProductQuantity())).
                collect(Collectors.toList());
        productInfoService.increaseStock(cartDtos);
        //如果已支付,需要退款
        if(orderMaster.getPayStatus().equals(OrderMasterPayStatusEnum.SUCCESS.getCode())){
            //TODO
        }
        return orderMasterDto;
    }

    @Override
    @Transactional
    public OrderMasterDto finish(OrderMasterDto orderMasterDto) {
        //判断订单状态
        if(!orderMasterDto.getOrderStatus().equals(OrderMasterOrderStatusEnum.NEW.getCode())){
            log.error("【完结订单】订单状态不正确,orderId={},status={}",orderMasterDto.getOrderId(),orderMasterDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //修改订单状态
        orderMasterDto.setOrderStatus(OrderMasterOrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderMasterDto,orderMaster);
        OrderMaster master = orderMasterDao.save(orderMaster);
        if(master==null){
            log.error("【完结订单】更新失败,orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderMasterDto;
    }

    @Override
    public OrderMasterDto paid(OrderMasterDto orderMasterDto) {
       //判断订单状态
        if(!orderMasterDto.getOrderStatus().equals(OrderMasterOrderStatusEnum.NEW.getCode())){
            log.error("【订单支付完成】订单状态不正确,orderId={},status={}",orderMasterDto.getOrderId(),orderMasterDto.getOrderStatus());
            throw new SellException(ResultEnum.ORDER_STATUS_ERROR);
        }
        //判断支付状态
        if(!orderMasterDto.getPayStatus().equals(OrderMasterPayStatusEnum.WAIT.getCode())){
            log.error("【订单支付完成】订单支付状态不正确,orderDto={}",orderMasterDto);
            throw new SellException(ResultEnum.ORDER_PAY_STATUS_ERROR);
        }
        //修改支付状态
        orderMasterDto.setPayStatus(OrderMasterPayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster=new OrderMaster();
        BeanUtils.copyProperties(orderMasterDto,orderMaster);
        OrderMaster master = orderMasterDao.save(orderMaster);
        if(master==null){
            log.error("【订单支付完成】更新失败，orderMaster={}",orderMaster);
            throw new SellException(ResultEnum.ORDER_UPDATE_FAIL);
        }
        return orderMasterDto;
    }
}
