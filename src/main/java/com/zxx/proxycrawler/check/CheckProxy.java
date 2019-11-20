package com.zxx.proxycrawler.check;

import cn.hutool.http.HttpRequest;
import com.zxx.proxycrawler.common.C;
import com.zxx.proxycrawler.entity.Proxy;
import com.zxx.proxycrawler.util.SettingUtil;

import java.net.InetSocketAddress;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 检查代理是否可用
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/20 14:06
 */
public class CheckProxy {


    /**
     * 检查代理是否可用的线程池
     */
    public static ThreadPoolExecutor proxyPool;

    static {
        proxyPool = new ThreadPoolExecutor(25, 50, 100L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000),new ThreadPoolExecutor.DiscardPolicy());
        proxyPool.allowCoreThreadTimeOut(true);
    }

    public static void start(Proxy proxy){
        //开始检查代理
        String url = SettingUtil.getValue("checkUrl");
        proxyPool.execute(new CheckThread(proxy,url));
    }

}
class CheckThread implements Runnable{

    private Proxy proxy;
    private String url;

    public CheckThread(Proxy proxy, String url) {
        this.proxy = proxy;
        this.url = url;
    }

    @Override
    public void run() {
        java.net.Proxy proxy1  = new java.net.Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress(proxy.getIp(),Integer.parseInt(proxy.getPort())));
        try {
            String data = HttpRequest.get(url).setProxy(proxy1).timeout(5000).execute().body();
            C.availableProxySet.add(proxy);
            System.out.println("当前代理请求成功Ip："+proxy.getIp()+"，port："+proxy.getPort());
        }catch (Exception e){
            System.out.println("当前代理请求超时Ip："+proxy.getIp()+"，port："+proxy.getPort());
        }
    }
}
