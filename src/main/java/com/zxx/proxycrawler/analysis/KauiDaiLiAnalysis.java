package com.zxx.proxycrawler.analysis;

import cn.hutool.core.date.DateUtil;
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



//    <tr>
//        <td data-title="IP">113.120.60.155</td>
//        <td data-title="PORT">9999</td>
//        <td data-title="匿名度">高匿名</td>
//        <td data-title="类型">HTTP</td>
//        <td data-title="位置">山东省济南市  电信</td>
//        <td data-title="响应速度">2秒</td>
//        <td data-title="最后验证时间">2019-11-19 22:31:01</td>
//    </tr>
    /**
     * 解析快代理
     */
    public static void parse(String html){
        Document document = Jsoup.parse(html);
        Elements elements = document.select("#list tbody tr");
        for (Element element:elements) {
            Proxy proxy = new Proxy();
            proxy.setIp(element.select("td[data-title=\"IP\"]").html());
            proxy.setPort(element.select("td[data-title=\"PORT\"]").html());
            proxy.setIsAnon(element.select("td[data-title=\"匿名度\"]").html());
            proxy.setType(element.select("td[data-title=\"类型\"]").html());
            proxy.setCountry(element.select("td[data-title=\"位置\"]").html());
            proxy.setSpeed(element.select("td[data-title=\"响应速度\"]").html());
            proxy.setEndTime(element.select("td[data-title=\"最后验证时间\"]").html());
        }
    }

}
