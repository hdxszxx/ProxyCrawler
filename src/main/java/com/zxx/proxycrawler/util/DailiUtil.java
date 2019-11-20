package com.zxx.proxycrawler.util;

import cn.hutool.json.JSONArray;
import com.zxx.proxycrawler.entity.Proxy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author zxx
 * @version 1.0
 * @date 2019/11/20 15:47
 */
public class DailiUtil {

    public static List<Proxy> getProxy(){
        String url = SettingUtil.getValue("outdaili");
        List<Proxy> daili = new ArrayList<Proxy>();
        try {
            System.out.println("开始获取本地缓存的代理");
            File file = new File(url);
            if (!file.exists()) {
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            FileReader fr = new FileReader(file.getAbsoluteFile());
            BufferedReader br = new BufferedReader(fr);
            StringBuffer sb = new StringBuffer();
            String a = null;
            while ((a = br.readLine())!=null){
                sb.append(a);
            }
            br.close();
            fr.close();
            JSONArray jsonArray = new JSONArray(sb.toString());
            daili = jsonArray.toList(Proxy.class);
            System.out.println("当前获取到的代理数为："+daili.size());
            return daili;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("当前获取到的代理数为："+daili.size());
        return daili;
    }
}
