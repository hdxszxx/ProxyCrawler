package com.zxx.proxycrawler;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.zxx.proxycrawler.util.RandomUtil;

import java.net.InetSocketAddress;
import java.util.HashMap;

/**
 * 启动类
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/19 18:27
 */
public class AppTest {
    public static void main(String[] args) {
        String url = "http://wegame.fyfsvcs.com.cn/2017.php";
        Value value = new Value(RandomUtil.getRandom(11),RandomUtil.getRandom(8),1);
        JSONObject jsonObject = new JSONObject(value);
        java.net.Proxy proxy1  = new java.net.Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress("59.57.149.249",9999));
        try {
            String data = HttpRequest.post(url).setProxy(proxy1).form(jsonObject.toBean(HashMap.class)).timeout(20000)
                    .execute().body();
            System.out.println(data);
        }catch (Exception e) {
        }
    }
}
