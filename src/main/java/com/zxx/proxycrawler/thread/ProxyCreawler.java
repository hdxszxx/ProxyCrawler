package com.zxx.proxycrawler.thread;

import cn.hutool.json.JSONArray;
import com.zxx.proxycrawler.check.CheckProxy;
import com.zxx.proxycrawler.common.C;
import com.zxx.proxycrawler.crawler.EightyNineDaiLiCrawler;
import com.zxx.proxycrawler.crawler.KuaiDaiLiCrawler;
import com.zxx.proxycrawler.crawler.XiCiDailiCrawler;
import com.zxx.proxycrawler.util.SettingUtil;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

    /**
     * 查询几页
     */
    public static int page = 10;

    public ProxyCreawler() {
        proxyPool = new ThreadPoolExecutor(50, 100, 100L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(1000),new ThreadPoolExecutor.DiscardPolicy());
        proxyPool.allowCoreThreadTimeOut(true);
    }

    public void startCrawl(){
        try {
            start();
        } catch (Exception e) {
            System.out.println("爬行出错");
        }
    }


    private void start() throws Exception {
        kuaiDaili();
        XiCiDaiLi();
        EightyNineDaiLi();
        //输出到文件内
        outJson();
        System.out.println(C.proxySet.toString());
    }

    /**
     * 89代理
     * @throws Exception
     */
    private void  EightyNineDaiLi() throws Exception{
        String EightyNineUrl = "http://www.89ip.cn/index_";
        for (int i=1;i<=page;i++) {
            String url = EightyNineUrl+i+".html";
            System.out.println("开始爬取："+url);
            proxyPool.execute(new EightyNineDaiLiCrawler(url));
            
        }
    }

    /**
     * 西刺代理
     */
    private void XiCiDaiLi() throws Exception{
        String nnUrl = "https://www.xicidaili.com/nn";
        String ntUrl = "https://www.xicidaili.com/nt";
        String wnUrl = "https://www.xicidaili.com/wn";
        String wtUrl = "https://www.xicidaili.com/wt";
        for (int i=1;i<=page;i++) {
            String url = nnUrl+"/"+i+"/";
            System.out.println("开始爬取："+url);
            proxyPool.execute(new XiCiDailiCrawler(url));
            
        }
        for (int i=1;i<=page;i++) {
            String url = ntUrl+"/"+i+"/";
            System.out.println("开始爬取："+url);
            proxyPool.execute(new XiCiDailiCrawler(url));
            
        }
        for (int i=1;i<=page;i++) {
            String url = wnUrl+"/"+i+"/";
            System.out.println("开始爬取："+url);
            proxyPool.execute(new XiCiDailiCrawler(url));
            
        }
        for (int i=1;i<=page;i++) {
            String url = wtUrl+"/"+i+"/";
            System.out.println("开始爬取："+url);
            proxyPool.execute(new XiCiDailiCrawler(url));
            
        }
    }

    /**
     * 快代理
     * @throws InterruptedException
     */
    private void kuaiDaili() throws Exception {
        String kauiInhaUrl = "https://www.kuaidaili.com/free/inha";
        String kauiIntrUrl = "https://www.kuaidaili.com/free/intr";
        //快代理开始查询
        for (int i=1;i<=page;i++) {
            String url = kauiInhaUrl+"/"+i+"/";
            System.out.println("开始爬取："+url);
            proxyPool.execute(new KuaiDaiLiCrawler(url));
        }
        for (int i=1;i<=page;i++) {
            String url = kauiIntrUrl+"/"+i+"/";
            System.out.println("开始爬取："+url);
            proxyPool.execute(new KuaiDaiLiCrawler(url));
            
        }
    }

    public void outJson(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (proxyPool != null) {
                        System.out.println("开始获取当前运行线程数proxyPool:"+proxyPool.getPoolSize());
                        System.out.println("开始获取当前运行线程数csproxyPool:"+ CheckProxy.proxyPool.getPoolSize());
                        if(proxyPool.getPoolSize() == 0 && CheckProxy.proxyPool.getPoolSize() == 0){
                            System.out.println("开始获取xiCiDaiLis:"+C.availableProxySet.size());
                            JSONArray json = new JSONArray(C.availableProxySet);
                            String url = SettingUtil.getValue("outdaili");
                            BufferedWriter bd = null;
                            try {
                                File file = new File(url);
                                if (!file.getParentFile().exists()) {
                                    file.getParentFile().mkdirs();
                                    file.createNewFile();
                                }
                                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                                bd = new BufferedWriter(fw);
                                bd.write(json.toString());
                                bd.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();
    }


}
