package com.zxx.proxycrawler.util;

import java.util.Random;

/**
 * 随机数
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/20 15:59
 */
public class RandomUtil {

    /**
     * 生成指定位数的随机数
     * @param length
     * @return
     */
    public static String getRandom(int length){
        String val = "";
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            val += String.valueOf(random.nextInt(10));
        }
        return val;
    }
    public static void main(String[] args) {
        System.out.println(getRandom(6));
    }
}
