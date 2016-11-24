/**
 * The Apache License 2.0
 * Copyright (c) 2016 Victor Zhang
 */
package org.zp.html.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @author Victor Zhang
 * @date 2016/11/24.
 */
public class JsoupTest {
    final String filePath = System.getProperty("user.dir")
            + "\\src\\test\\resources\\html\\example.html";

    Document docFromStr;
    Document docFromUrl;
    Document docFromFile;

    @Before
    public void before() throws IOException {
        // 从一个html字符串加载Document对象
        final String html = "<html><head><title>First parse</title></head>"
                + "<body><p>Parsed HTML into a doc.</p></body></html>";
        docFromStr = Jsoup.parse(html);

        // 从一个URL加载Document对象
        docFromUrl = Jsoup.connect("https://www.baidu.com/").get();

        // 从一个文件加载Document对象
        File input = new File(filePath);
        docFromFile = Jsoup.parse(input, "UTF-8", "");
    }

    @Test
    public void testGetHeadAndBody() {
        Element head = docFromStr.head();
        Element body = docFromStr.body();
        System.out.println("head内容：\n" + head.toString());
        System.out.println("body内容：\n" + body.toString());
    }

    /**
     * 使用DOM方法来遍历一个文档
     */
    @Test
    public void test01() {
        // 遍历一个Document对象中所有的链接
        Element content = docFromUrl.body();
        Elements links = content.getElementsByTag("a");
        for (Element link : links) {
            System.out.println("linkHref: " + link.attr("href"));
            System.out.println("linkText: " + link.text());
        }
    }

    /**
     * 使用选择器语法来查找元素
     */
    @Test
    public void testSelect() {
        //带有href属性的a元素
        Elements hrefs = docFromUrl.select("a[href]");
        System.out.println("[hrefs]\n" + hrefs.toString());
        //扩展名为.png的图片
        Elements pngs = docFromUrl.select("img[src$=.png]");
        System.out.println("[pngs]\n" + pngs.toString());
        //class等于masthead的div标签
        Element head_wrappers = docFromUrl.select("div.head_wrapper").first();
        System.out.println("[head_wrapper:]\n" + head_wrappers.toString());
        //在h3元素之后的a元素
        Elements resultLinks = docFromUrl.select("div.head_wrapper > a");
        System.out.println("[resultLinks]\n" + resultLinks.toString());
    }
}
