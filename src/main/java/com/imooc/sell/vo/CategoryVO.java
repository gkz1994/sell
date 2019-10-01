package com.imooc.sell.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

/**
 * @ClassName CategoryVo
 * @Description 返回给前端的类目层
 * @Author gkz
 * @Date 2019/9/27 19:17
 * @Version 1.0
 **/
@Data
public class CategoryVO {

    @JsonProperty("name")
    private String categoryName;

    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO>categoryFoods;
}
