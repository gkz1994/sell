package com.imooc.sell.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @ClassName ProductCategory
 * @Description 商品类目实体类
 * @Author gkz
 * @Date 2019/9/25 20:31
 * @Version 1.0
 **/
@Entity
@Data
@DynamicUpdate
public class ProductCategory {

    @Id
    @GeneratedValue
    private int categoryId;

    private String categoryName;

    private int categoryType;

    public ProductCategory() {
    }

    public ProductCategory(String categoryName, int categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
