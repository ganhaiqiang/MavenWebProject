package com.demo.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

/**
 * Spring容器启动完毕执行
 * 
 * @author 80001267
 *
 */
public class BeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if ("Root WebApplicationContext".equals(event.getApplicationContext().getDisplayName())) {
			System.out.println("Spring容器启动完毕了。。。。。。。。");
		}
	}

}
