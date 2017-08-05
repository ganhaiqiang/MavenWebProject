package com.demo.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import com.demo.service.StudentService;

/**
 * Spring容器启动完毕执行
 * 
 * @author 80001267
 *
 */
public class BeanPostProcessor implements ApplicationListener<ContextRefreshedEvent> {
	@Autowired
	private StudentService StudentService;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if ("Root WebApplicationContext".equals(event.getApplicationContext().getDisplayName())) {
			System.out.println("Spring容器启动完毕了。。。。。。。。");

			// System.out.println(JSON.toJSONString(StudentService.getAll(),
			// true));
		}
	}

}
