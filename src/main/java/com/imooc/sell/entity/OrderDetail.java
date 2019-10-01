package com.imooc.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ClassName OrderDetail
 * @Description 订单详情实体类
 * @Author gkz
 * @Date 2019/9/29 13:29
 * @Version 1.0
 **/
@Entity
@Data
@DynamicUpdate
public class OrderDetail {

    /**订单详情id*/
    @Id
    private String detailId;

    /**订单Id*/
    private String orderId;

    /**商品id*/
    private String productId;

    /**商品名称*/
    private String productName;

    /**商品单价*/
    private BigDecimal productPrice;

    /**商品数量*/
    private Integer productQuantity;

    /**商品小图*/
    private String productIcon;


}
