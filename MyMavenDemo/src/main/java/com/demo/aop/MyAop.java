package com.demo.aop;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;

//@Aspect
//@Component
public class MyAop {

	/**
	 * Pointcut 定义Pointcut，Pointcut的名称为aspectjMethod()，此方法没有返回值和参数
	 * 该方法就是一个标识，不进行调用
	 */
	// @Pointcut("execution(* com.demo.controller.ParamController.*(..))")
	private void aspectjMethod() {
	};
	// @Pointcut("execution(* com.demo.service.impl.StudentServiceImpl.*(..))")
	// private void aspectjMethod() {
	// };

	/**
	 * Before 在核心业务执行前执行，不能阻止核心业务的调用。
	 * 
	 * @param joinPoint
	 */
	// @Before(value = "aspectjMethod()")
	public void beforeAdvice(JoinPoint joinPoint) {
		System.out.println("-----beforeAdvice().invoke-----");
		System.out.println("*****************************");
		System.out.println(joinPoint.getSignature().getDeclaringTypeName());
		System.out.println(joinPoint.getSignature().getName());
		System.out.println(joinPoint.getSignature().toLongString());
		System.out.println(joinPoint.getSignature().toShortString());
		System.out.println("*****************************");
		System.out.println(" 此处意在执行核心业务逻辑前，做一些安全性的判断等等");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("args:" + JSON.toJSONString(joinPoint.getArgs()));
		System.out.println("-----End of beforeAdvice()------");
	}

	/**
	 * After 核心业务逻辑退出后（包括正常执行结束和异常退出），执行此Advice
	 * 
	 * @param joinPoint
	 */
	// @After(value = "aspectjMethod()")
	public void afterAdvice(JoinPoint joinPoint) {
		System.out.println("-----afterAdvice().invoke-----");
		System.out.println(" 此处意在执行核心业务逻辑之后，做一些日志记录操作等等");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("args:" + JSON.toJSONString(joinPoint.getArgs()));
		System.out.println("-----End of afterAdvice()------");
	}

	/**
	 * Around 手动控制调用核心业务逻辑，以及调用前和调用后的处理,
	 * 
	 * 注意：当核心业务抛异常后，立即退出，转向AfterAdvice 执行完AfterAdvice，再转到ThrowingAdvice
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	// @Around(value = "aspectjMethod()")
	public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("-----aroundAdvice().invoke-----");
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		Map<String, String[]> paramsMap = request.getParameterMap();
		Map<String, Object> map = new HashMap<String, Object>();
		for (Entry<String, String[]> entry : paramsMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue()[0]);
		}
		System.out.println(JSON.toJSONString(map, true));
		System.out.println(" 此处可以做类似于Before Advice的事情");
		System.out.println("methed:" + pjp.getTarget().toString() + "args:" + JSON.toJSONString(pjp.getArgs()));

		// 调用核心逻辑
		Object retVal = pjp.proceed();
		Signature signature = pjp.getSignature();
		Class returnType = ((MethodSignature) signature).getReturnType();
		System.out.println(returnType.getName());
		// ArrayVo vo=(ArrayVo) retVal;
		// System.out.println(JSON.toJSONString(vo, true));
		System.out.println(" 此处可以做类似于After Advice的事情");
		System.out.println("-----End of aroundAdvice()------");
		return retVal;
	}

	/**
	 * AfterReturning 核心业务逻辑调用正常退出后，不管是否有返回值，正常退出后，均执行此Advice
	 * 
	 * @param joinPoint
	 */
	// @AfterReturning(value = "aspectjMethod()", returning = "retVal")
	public void afterReturningAdvice(JoinPoint joinPoint, String retVal) {
		System.out.println("-----afterReturningAdvice().invoke-----");
		System.out.println("Return Value: " + retVal);
		System.out.println(" 此处可以对返回值做进一步处理");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("-----End of afterReturningAdvice()------");
	}

	/**
	 * 核心业务逻辑调用异常退出后，执行此Advice，处理错误信息
	 * 
	 * 注意：执行顺序在Around Advice之后
	 * 
	 * @param joinPoint
	 * @param ex
	 */
	// @AfterThrowing(value = "aspectjMethod()", throwing = "ex")
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
		System.out.println("-----afterThrowingAdvice().invoke-----");
		System.out.println(" 错误信息：" + ex.getMessage());
		System.out.println(" 此处意在执行核心业务逻辑出错时，捕获异常，并可做一些日志记录操作等等");
		System.out.println(" 可通过joinPoint来获取所需要的内容");
		System.out.println("-----End of afterThrowingAdvice()------");
	}
}