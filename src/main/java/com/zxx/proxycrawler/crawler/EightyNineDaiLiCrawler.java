package com.zxx.proxycrawler.crawler;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.zxx.proxycrawler.analysis.EightyNineDaiLiAnalysis;
import com.zxx.proxycrawler.common.C;
import com.zxx.proxycrawler.entity.Proxy;

import java.net.InetSocketAddress;
import java.util.Set;

/**
 * 89免费代理
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/20 11:53
 */
public class EightyNineDaiLiCrawler implements Runnable{

    private String url;

    public EightyNineDaiLiCrawler(String url) {
        this.url = url;
    }

    /**
     * 89代理
     */
    @Override
    public void run() {
        for (int i=0;i<=100;i++){
            if(i==0){
                if(get(null)){
                    return;
                }
            }else{
                if(C.availableProxySet.size()>0){
                    for(Proxy proxy:C.availableProxySet){
                        if(get(proxy)){
                            return;
                        }
                    }
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    private boolean get(Proxy proxy){
        try {
            if(proxy == null) {
                String data = HttpUtil.get(url);
                Set<Proxy> proxies = EightyNineDaiLiAnalysis.parse(data);
                C.proxySet.addAll(proxies);
                return true;
            }else{
                java.net.Proxy proxy1  = new java.net.Proxy(java.net.Proxy.Type.HTTP,new InetSocketAddress(proxy.getIp(),Integer.parseInt(proxy.getPort())));
                String data = HttpRequest.get(url).setProxy(proxy1).timeout(5000).execute().body();
                Set<Proxy> proxies = EightyNineDaiLiAnalysis.parse(data);
                C.proxySet.addAll(proxies);
                return true;
            }
        }catch (Exception e){
            System.out.println("链接失败url："+url);
        }
        return false;
    }
}
