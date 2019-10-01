package com.imooc.sell.entity;

import com.imooc.sell.enums.OrderMasterOrderStatusEnum;
import com.imooc.sell.enums.OrderMasterPayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName OrderMaster
 * @Description 订单主表实体类
 * @Author gkz
 * @Date 2019/9/29 13:15
 * @Version 1.0
 **/
@Entity
@Data
@DynamicUpdate
public class OrderMaster {

    /**订单id*/
    @Id
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
}
