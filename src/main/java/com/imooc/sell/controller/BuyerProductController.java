package com.imooc.sell.controller;

import com.imooc.sell.entity.ProductCategory;
import com.imooc.sell.entity.ProductInfo;
import com.imooc.sell.service.ProductCategoryService;
import com.imooc.sell.service.ProductInfoService;
import com.imooc.sell.utils.ResultVoUtils;
import com.imooc.sell.vo.CategoryVO;
import com.imooc.sell.vo.ProductInfoVO;
import com.imooc.sell.vo.ResultVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BuyerProductController
 * @Description 返回给前端的控制层
 * @Author gkz
 * @Date 2019/9/27 19:13
 * @Version 1.0
 **/
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/list")
    public ResultVO list(){
        //查询所有在架的商品
        List<ProductInfo> infoList = productInfoService.findUpAll();
        List<Integer>categoryTypeList=new ArrayList<>();
        for (ProductInfo info:infoList) {
            categoryTypeList.add(info.getCategoryType());
        }
        List<ProductCategory> categoryList = productCategoryService.findCategoryByCategoryTypeIn(categoryTypeList);
        List<CategoryVO>categoryVoList=new ArrayList<>();
        for (ProductCategory category:categoryList) {
            CategoryVO categoryVO=new CategoryVO();
            categoryVO.setCategoryName(category.getCategoryName());
            categoryVO.setCategoryType(category.getCategoryType());
            List<ProductInfoVO>productInfoVoList=new ArrayList<>();
            for (ProductInfo info:infoList) {
                if(info.getCategoryType().equals(category.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(info, productInfoVO);
                    productInfoVoList.add(productInfoVO);
                }

            }
            categoryVO.setCategoryFoods(productInfoVoList);
            categoryVoList.add(categoryVO);
        }

       return ResultVoUtils.success(categoryVoList);
    }
}
