package com.oukele.csdn_demo;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class CsdnCrawlDemo {

    public static void main(String[] args) {
        //目标地址
        String url = "https://www.csdn.net/";
        try {
            Document document = Jsoup
                    .connect(url)
                    .header("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.80 Safari/537.36")
                    .get();
            //  右侧导航栏
            Elements nav_com = document.getElementsByClass("nav_com");
            Elements elements = nav_com.select("ul>li");
            for (Element element : elements) {
                System.out.println("导航标题："+element.text()+"\t标题访问地址："+element.select("a").attr("href")+"\r\n");
                //这里 我们可以 根据 得到的访问链接 再进行 爬取.........
            }
        } catch (IOException e) {
            System.out.println("出现错误:"+e.getMessage());
        }


    }

}
