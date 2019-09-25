package com.imooc.sell.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

/**
 * @ClassName ProductInfo
 * @Description 商品实体类
 * @Author gkz
 * @Date 2019/9/25 21:21
 * @Version 1.0
 **/
@Data
@Entity
public class ProductInfo {

    @Id
    /**商品id*/
    private String productId;
    /**商品名称*/
    private String productName;
    /**单价*/
    private BigDecimal productPrice;
    /**库存*/
    private int productStock;
    /**描述*/
    private String productDescription;
    /**小图*/
    private String productIcon;
    /**商品状态,0正常1下架*/
    private Integer productStatus;
    /**类目编号*/
    private int categoryType;

}
