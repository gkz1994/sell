package com.imooc.sell.dto;

import com.imooc.sell.entity.OrderDetail;
import com.imooc.sell.enums.OrderMasterOrderStatusEnum;
import com.imooc.sell.enums.OrderMasterPayStatusEnum;
import lombok.Data;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @ClassName OrderMasterDto
 * @Description 数据传输Dto类
 * @Author gkz
 * @Date 2019/10/1 20:15
 * @Version 1.0
 **/
@Data
public class OrderMasterDto {
    /**订单id*/
    private String orderId;

    /**买家名字*/
    private String buyerName;

    /**买家电话*/
    private String buyerPhone;

    /**买家地址*/
    private String buyerAddress;

    /**买家微信openId*/
    private String buyerOpenid;

    /**订单总金额*/
    private BigDecimal orderAmount;

    /**订单状态,默认0新下单*/
    private Integer orderStatus = OrderMasterOrderStatusEnum.NEW.getCode();

    /**支付状态,默认0未支付*/
    private Integer payStatus = OrderMasterPayStatusEnum.WAIT.getCode();

    /**创建时间*/
    private Date createTime;

    /**更新时间*/
    private Date updateTime;

    List<OrderDetail>orderDetailList;
}
