package com.mayikt.db.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import com.mayikt.db.config.DataSourceContextHolder;
/**
 * 1、使用AOP动态切换不同的数据源
 * 项目名称：springboot-mybatis-02 
 * 类名称：SwitchDataSourceAOP
 * 开发者：Lenovo
 * 开发时间：2019年4月22日上午10:51:22
 */
@Aspect
@Component
@Lazy(false)
@Order(0) // Order设定AOP执行顺序 使之在数据库事务上先执行
public class SwitchDataSourceAOP {
	// 这里切到你的方法目录
	@Before("execution(* com.mayikt.service.*.*(..))")
	public void process(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		if (methodName.startsWith("get") || methodName.startsWith("count") || methodName.startsWith("find")
				|| methodName.startsWith("list") || methodName.startsWith("select") || methodName.startsWith("check")) {
			//读数据源
			DataSourceContextHolder.setDbType("selectDataSource");
		} else {
			// 写数据源切换dataSource
			DataSourceContextHolder.setDbType("updateDataSource");
		}
	}
}