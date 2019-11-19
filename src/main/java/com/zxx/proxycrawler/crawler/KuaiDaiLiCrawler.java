package com.zxx.proxycrawler.crawler;

import cn.hutool.http.HttpUtil;

/**
 * 快代理
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/19 20:53
 */
public class KuaiDaiLiCrawler implements Runnable {

    private String url;

    public KuaiDaiLiCrawler(String url) {
        this.url = url;
    }

    /**
     * 快代理
     */
    public void run() {
        try {
            String data = HttpUtil.get(url);

        }catch (Exception e){
            System.out.println("链接失败url："+url);
        }
    }
}
