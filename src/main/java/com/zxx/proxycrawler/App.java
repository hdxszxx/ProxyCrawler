package com.zxx.proxycrawler;

import com.zxx.proxycrawler.thread.ProxyCreawler;

import java.io.IOException;

/**
 * 启动类
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/19 18:27
 */
public class App {
    public static void main(String[] args) throws IOException {
        //开始爬取
       new ProxyCreawler().startCrawl();

    }
}
