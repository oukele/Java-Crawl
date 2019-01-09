package com.oukele.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

//测试抓取的数据条数
public class CrawlTest {

    public static void main(String[] args) throws IOException {
//        http://www.zhsme.gov.cn/policy/getPolicyList?pageNum=4
        String url = "http://www.zhsme.gov.cn/policy/getPolicyList?pageNum=";
        Document document = null;
        try {
            document = Jsoup
                    //目标网址
                    .connect(url + 1)
                    .timeout(5000)
                    //设置 头部信息
                    // User-Agent  用户代理 简称 UA，它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36")
                    .get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 总页数
        String listfoot = document.getElementsByClass("listfoot").html();
        String substring = listfoot.substring((listfoot.lastIndexOf("totalPages")), listfoot.indexOf("visiblePages")).trim();
        int tatol = Integer.parseInt(substring.substring(substring.indexOf("totalPages:") + 11, substring.indexOf(",")));

        //抓取 失败的页数 记录
        int error = 0;
        //抓取 成功的页数 记录
        int ok = 0;
        int i = 1;
        for (; i <= tatol; i++) {
            try {
                crawlData(url + i);
                ok++;
                System.out.println("第" + i + "页的数据抓取完毕。\r\n");
            } catch (Exception e) {
                error++;
                System.out.println("第" + i + "页抓取失败");
                continue;
            }
        }

        System.out.println("抓取成功的条数:" + (ok * 10) + "\t" + "失败的条数:" + (error * 10));

    }

    //获取第1个数据
    public static void crawlData(String url) throws Exception {
        Document document = Jsoup
                //目标网址
                .connect(url)
                .timeout(5000)
                //设置 头部信息
                // User-Agent  用户代理 简称 UA，它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36")
                .get();
        //内容区
        Elements conten = document.getElementsByClass("list-content list-content-1");
        Elements ulLi = conten.select("ul li div");
        for (Element element : ulLi) {
            //链接
            String link = element.select("h4 a").attr("href");
            crawlData1("http://www.zhsme.gov.cn" + link);
        }
    }

    //获取第2个数据
//    url="e2d41e70Meb2eM4fe0M9e8bM29a32753e56f";
    public static void crawlData1(String url) throws Exception {

        Document document = Jsoup
                //目标网址
                .connect(url)
                //设置 头部信息
                // User-Agent  用户代理 简称 UA，它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36")
                .get();
        Elements elementsByClass = document.getElementsByClass("policy-info").select("div");
        String origin = elementsByClass.eq(3).select("a").text();
        //政策源id
        String origin_Id = elementsByClass.eq(3).select("a").attr("onclick");
        String originId = origin_Id.substring(origin_Id.indexOf("toTextPolicyView('") + 18, origin_Id.indexOf("')"));
        crawlData2("http://www.zhsme.gov.cn/policy/getTextPolicyByTextPolicyId?textPolicyId=" + originId);
    }

    //获取第3个数据
//     url = "http://www.zhsme.gov.cn/policy/getTextPolicyByTextPolicyId?textPolicyId=813fd8e1-c52c-4956-a5c6-32cefbfbc5ea";
    public static void crawlData2(String url) throws Exception {
        Document document = Jsoup
                //目标网址
                .connect(url)
                //设置 头部信息
                // User-Agent  用户代理 简称 UA，它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36")
                .get();
        Elements main = document.getElementsByClass("view");
        Elements content = main.select(".view-content > p");
    }

}
