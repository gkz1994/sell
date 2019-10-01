package com.imooc.sell.utils;

import com.imooc.sell.vo.ResultVO;

/**
 * @ClassName ResultVoUtils
 * @Description TODO
 * @Author gkz
 * @Date 2019/9/27 22:22
 * @Version 1.0
 **/
public class ResultVoUtils {

    public static ResultVO success(Object obj){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(obj);
        return resultVO;
    }

    public static ResultVO success(){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        resultVO.setData(null);
        return resultVO;
    }

    public static ResultVO error(Integer code,String msg){
        ResultVO resultVO=new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
}
