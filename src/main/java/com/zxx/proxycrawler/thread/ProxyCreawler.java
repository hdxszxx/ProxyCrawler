package com.zxx.proxycrawler.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 爬取代理
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/19 20:31
 */
public class ProxyCreawler {

    /**
     * 获取代理的线程池
     */
    public static ThreadPoolExecutor proxyPool;

    public ProxyCreawler() {
        proxyPool = new ThreadPoolExecutor(50, 100, 100L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000),new ThreadPoolExecutor.DiscardPolicy());
    }

    public void startCrawl(){
        start();
    }


    private void start(){

    }

}
