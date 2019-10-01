package com.imooc.sell.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.imooc.sell.enums.ProductInfoStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName ProductInfo
 * @Description 商品实体类
 * @Author gkz
 * @Date 2019/9/25 21:21
 * @Version 1.0
 **/
@Data
@Entity
@DynamicUpdate
public class ProductInfo {

    @Id
    /**商品id*/
    private String productId;
    /**商品名称*/
    private String productName;
    /**单价*/
    private BigDecimal productPrice;
    /**库存*/
    private Integer productStock;
    /**描述*/
    private String productDescription;
    /**小图*/
    private String productIcon;
    /**商品状态,0正常1下架*/
    private Integer productStatus= ProductInfoStatusEnum.UP.getCode();
    /**类目编号*/
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


}
