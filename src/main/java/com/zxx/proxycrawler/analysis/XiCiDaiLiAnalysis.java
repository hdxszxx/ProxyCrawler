package com.zxx.proxycrawler.analysis;

import com.zxx.proxycrawler.check.CheckProxy;
import com.zxx.proxycrawler.entity.Proxy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashSet;
import java.util.Set;

/**
 * 西刺解析
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/19 21:00
 */
public class XiCiDaiLiAnalysis {



//<tr class="odd">
//      <td class="country"><img src="//fs.xicidaili.com/images/flag/cn.png" alt="Cn"></td>
//      <td>27.152.90.6</td>
//      <td>9999</td>
//      <td>
//        <a href="/2019-11-01/fujian">福建泉州</a>
//      </td>
//      <td class="country">高匿</td>
//      <td>HTTP</td>
//      <td class="country">
//        <div title="0.546秒" class="bar">
//          <div class="bar_inner fast" style="width:99%">
//
//          </div>
//        </div>
//      </td>
//      <td class="country">
//        <div title="0.109秒" class="bar">
//          <div class="bar_inner fast" style="width:98%">
//
//          </div>
//        </div>
//      </td>
//
//      <td>18天</td>
//      <td>19-11-20 11:20</td>
//    </tr>
    /**
     * 解析西刺代理
     */
    public static  Set<Proxy> parse(String html){
        Document document = Jsoup.parse(html);
        Elements elements = document.select("#ip_list tr[class]");
        Set<Proxy> proxies = new HashSet<Proxy>();
        for (Element element:elements) {
            Proxy proxy = new Proxy();
            proxy.setIp(element.select("td:eq(1)").html());
            proxy.setPort(element.select("td:eq(2)").html());
            proxy.setIsAnon(element.select("td:eq(4)").html());
            proxy.setType(element.select("td:eq(5)").html());
            proxy.setCountry(element.select("td:eq(3) a").html());
            proxy.setSpeed(element.select("td:eq(6) div[class='bar']").attr("title"));
            proxy.setEndTime(element.select("td:eq(9)").html());
            //开始检查是否可以用
            CheckProxy.start(proxy);
            proxies.add(proxy);
        }
        return proxies;
    }

}
