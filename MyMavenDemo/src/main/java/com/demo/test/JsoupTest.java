package com.demo.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupTest {
	public static void main(String[] args) {
		try {
			Document document=Jsoup.connect("http://www.baidu.com").get();
			Elements elements=document.getElementsByTag("title");
			for (Element element : elements) {
				System.out.println(element.text());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
