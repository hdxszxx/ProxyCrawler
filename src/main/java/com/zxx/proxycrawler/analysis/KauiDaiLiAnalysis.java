package com.zxx.proxycrawler.analysis;

import com.zxx.proxycrawler.entity.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 快代理解析
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/19 21:00
 */
public class KauiDaiLiAnalysis {


    /**
     * 解析快代理
     */
    public static void parse(String html){
        Document document = Jsoup.parse(html);
        Elements elements = document.select("#list tbody tr");
        for (Element element:elements) {
            Proxy proxy = new Proxy();
            proxy.setIp(element.select("td[data-title=\"IP\"]").html());
            proxy.setPort(element.select("td[data-title=\"IP\"]").html());
            proxy.setIsAnon(element.select("td[data-title=\"IP\"]").html());
            proxy.setType(element.select("td[data-title=\"IP\"]").html());
            proxy.setCountry(element.select("td[data-title=\"IP\"]").html());
            proxy.setSpeed(element.select("td[data-title=\"IP\"]").html());
//            proxy.setEndTime();
        }
    }

}
