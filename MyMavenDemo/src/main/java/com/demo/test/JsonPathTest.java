package com.demo.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.demo.vo.Bicycle;
import com.demo.vo.Book;
import com.demo.vo.JSONPathVo;
import com.demo.vo.Store;

public class JsonPathTest {
	public static void main(String[] args) {
		Book book1 = new Book("Nigel Rees", "Sayings of the Century", "reference", 8.95, null);
		Book book2 = new Book("Evelyn Waugh", "Sword of Honour", "fiction", 12.99, null);
		Book book3 = new Book("Herman Melville", "Moby Dick", "fiction", 8.99, "0-553-21311-3");
		Book book4 = new Book("J. R. R. Tolkien", "The Lord of the Rings", "fiction", 22.99, "0-395-19395-8");
		List<Book> book = new ArrayList<Book>();
		book.add(book1);
		book.add(book2);
		book.add(book3);
		book.add(book4);
		Bicycle bicycle = new Bicycle(19.95, "red");
		Store store = new Store(book, bicycle);
		JSONPathVo vo = new JSONPathVo(store);
		Object object = JSONObject.parse(JSON.toJSONString(vo));

		// 所有书籍作者
		List<String> authorList = (List<String>) JSONPath.eval(object, "$.store.book.author");
		System.out.println(authorList);
		// 所有具有isbn属性的第一本书籍的价格
		String color = (String) JSONPath.eval(object, "$.store.bicycle.color");
		System.out.println(color);
		// 所有具有isbn属性的第一本书籍的价格
		BigDecimal priceIsbn = (BigDecimal) JSONPath.eval(object, "$.store.book[?(@.isbn)][0].price");
		System.out.println(priceIsbn);
		// 价格大于10元的书籍
		List<Book> bookList = (List<Book>) JSONPath.eval(object, "$.store.book[?(@.price>10)]");
		System.out.println(JSON.toJSONString(bookList, true));
		// 具有isbn属性并且价格大于10元的书籍
		List<Book> books = (List<Book>) JSONPath.eval(object, "$.store.book[?(@.isbn)][?(@.price>10)]");
		System.out.println(JSON.toJSONString(books, true));
		// 第三本书的价格
		BigDecimal priceThird = (BigDecimal) JSONPath.eval(object, "$.store.book[2].price");
		System.out.println(priceThird);
		// 最后一本书的价格
		BigDecimal priceEnd = (BigDecimal) JSONPath.eval(object, "$.store.book[-1].price");
		System.out.println(priceEnd);
		// 前两本书的价格
		List<BigDecimal> priceTwo = (List<BigDecimal>) JSONPath.eval(object, "$.store.book[0,1].price");
		System.out.println(priceTwo);
		// 所有价格大于10元的书籍的价格
		List<Double> priceList = (List<Double>) JSONPath.eval(object, "$.store.book[?(@.price > 10)].price");
		System.out.println(priceList);
		// 价格等于8.95元的书籍
		List<Book> priceAny = (List<Book>) JSONPath.eval(object, "$.store.book[?(@.price = 8.95)]");
		System.out.println(priceAny);
		// 作者是Nigel Rees的所有书籍（等号两边必须有空格）
		List<Book> jrrtList = (List<Book>) JSONPath.eval(object, "$.store.book[?(@.author = 'J. R. R. Tolkien')]");
		System.out.println(jrrtList);

	}
}
