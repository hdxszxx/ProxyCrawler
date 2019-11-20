package com.zxx.proxycrawler;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.zxx.proxycrawler.util.RandomUtil;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * 启动类
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/19 18:27
 */
public class AppTest {
    public static void main(String[] args) {
//        String url = "http://wegame.fyfsvcs.com.cn/2017.php";
//        Value value = new Value(RandomUtil.getRandom(11),RandomUtil.getRandom(8),1);
//        JSONObject jsonObject = new JSONObject(value);
//        java.net.Proxy proxy1  = new java.net.Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress("59.57.149.249",9999));
//        try {
//            String data = HttpRequest.post(url).setProxy(proxy1).form(jsonObject.toBean(HashMap.class)).timeout(20000)
//                    .execute().body();
//            System.out.println(data);
//        }catch (Exception e) {
//        }
        Map<String,Object> header = new HashMap<>();
        for (int i=0;i<500;i++
             ) {


        HttpRequest.get("https://www.peixun69.com").header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3")
                .header("Accept-Encoding","gzip, deflate, br")
                .header("Accept-Language","zh-CN,zh;q=0.9")
                .header("Cache-Control","max-age=0")
                .header("Connection","keep-alive")
                .header("Cookie","Hm_lvt_50d053e54fd74ecded9146be0aba52de=1574246268; Hm_lpvt_50d053e54fd74ecded9146be0aba52de=1574246827")
                .header("Host","www.peixun69.com")
                .header("If-Modified-Since","Wed, 20 Nov 2019 09:16:30 GMT")
                .header("If-None-Match","W/\"5dd5046e-39d5\"")
                .header("Sec-Fetch-Site","navigate")
                .header("Sec-Fetch-Mode","cross-site")
                .header("Sec-Fetch-User","?1")
                .header("Upgrade-Insecure-Requests","1")
                .header("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.90 Safari/537.36")
                .execute().body();
    }
    }
}
