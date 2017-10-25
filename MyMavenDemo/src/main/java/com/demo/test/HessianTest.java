package com.demo.test;

import java.net.MalformedURLException;

import com.caucho.hessian.client.HessianProxyFactory;
import com.demo.service.IHessianService;

public class HessianTest {
	public static void main(String[] args) {
		// Spring Hessian代理Servelet  
		String url = "http://localhost:8089/SST_DCN/service/hessianService";
		HessianProxyFactory factory = new HessianProxyFactory();
		try {
			IHessianService service = (IHessianService) factory.create(IHessianService.class, url);
			System.out.println(service.getMap());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}
}
