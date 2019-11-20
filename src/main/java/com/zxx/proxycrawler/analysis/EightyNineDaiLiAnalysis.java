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
 * 89代理
 *
 * @author zxx
 * @version 1.0
 * @date 2019/11/19 21:00
 */
public class EightyNineDaiLiAnalysis {



//    <tbody>
//	<tr>
//		<td>
//			49.233.189.160		</td>
//		<td>
//			808		</td>
//		<td>
//    北京市		</td>
//		<td>
//    教育网		</td>
//		<td>
//			2019/11/20 11:45:02		</td>
//	</tr>
//	</tbody>

    /**
     * 解析89代理
     */
    public static  Set<Proxy> parse(String html){
        Document document = Jsoup.parse(html);
        Elements elements = document.select(".layui-table tbody tr");
        Set<Proxy> proxies = new HashSet<Proxy>();
        for (Element element:elements) {
            Proxy proxy = new Proxy();
            proxy.setIp(element.select("td:eq(0)").html());
            proxy.setPort(element.select("td:eq(1)").html());
            proxy.setCountry(element.select("td:eq(2)").html());
            proxy.setEndTime(element.select("td:eq(4)").html());
            //开始检查是否可以用
            CheckProxy.start(proxy);
            proxies.add(proxy);
        }
        return proxies;
    }

}
