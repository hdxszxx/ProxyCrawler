package com.zxx.proxycrawler.common;

import com.zxx.proxycrawler.entity.Proxy;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 公共参数类
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/20 10:22
 */
public class C {
    /**
     * 线程安全的set集合 存储爬行到的代理
     */
    public static Set<Proxy> proxySet = new CopyOnWriteArraySet<Proxy>();

    /**
     * 线程安全的set集合 存储爬行到的可用代理
     */
    public static Set<Proxy> availableProxySet = new CopyOnWriteArraySet<Proxy>();
}
