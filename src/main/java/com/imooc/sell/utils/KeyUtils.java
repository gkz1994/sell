package com.imooc.sell.utils;

import java.util.Random;

/**
 * @ClassName KeyUtils
 * @Description TODO
 * @Author gkz
 * @Date 2019/10/1 20:54
 * @Version 1.0
 **/
public class KeyUtils {

    /***
     * 生成唯一的主键：时间加上随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random=new Random();
        Integer number=random.nextInt(900000)+100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }
}
