package com.oukele.demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//抓取 通知公告的信息 (未处理完，但是可以用)
public class Crawl {


    public static void main(String[] args) {
        try {

            StringBuffer sbf = new StringBuffer();
            List<String> noticeList = new ArrayList<String>();

            Document document = Jsoup.
                    // 目标 URL
                            connect("http://www.zhsme.gov.cn/main/toMainIndex")
                    // 设置 请求头部
                    //User-Agent --> 用户代理 简称 UA，它是一个特殊字符串头，使得服务器能够识别客户使用的操作系统及版本、CPU 类型、浏览器及版本、浏览器渲染引擎、浏览器语言、浏览器插件等
                    .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36")
                    // 执行 get 请求 并且 获取 网页信息
                    .get();

            // 网页名
            String title = document.select("title").text();
            //获取 通知公告的信息
            Elements noticeElements = document.getElementsByClass("news-list left");
            //公告 标题
            String noticeTitle = noticeElements.select("div h5").first().text();
            //获取 公告子标题 与 链接
            Elements noticeUlLi = noticeElements.select("ul li");
            //获取 链接 与 链接标题
            for (Element element : noticeUlLi) {
                //将 链接 存入 集合中
                noticeList.add(element.select("a").attr("href"));
                sbf.append("链接标题：" + element.select("a").attr("title") + " 网页链接：" + element.select("a").attr("href") + "")
                        .append(" 标签中的标题：" + element.select("a").text() + " 时间：" + element.select("span").text() + "\r\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //查询链接的信息
    public void select(String url) throws IOException {

        Document noticeInfoDocument = Jsoup.connect(url)
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36")
                .get();
        //主要信息节点
        Elements view = noticeInfoDocument.getElementsByClass("view");
        //标头
        String noticeTitle1 = view.select("h1 strong").text();
        // 此信息的来源 与发布时间 与 浏览次数
        Elements noticeOrigin = view.select("h2 span");
        //noticeOrigin.eq(0).text()+"\t" +noticeOrigin.eq(1).text()+ "\t" + noticeOrigin.eq(2).text();
        //主题内容
        Elements mainView = view.select("div > p");
        //mainView.text();
    }

}
