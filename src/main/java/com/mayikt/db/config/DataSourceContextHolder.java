package com.mayikt.db.config;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
/**
 * 1、本地保存数据源
 * 项目名称：springboot-mybatis-02 
 * 类名称：DataSourceContextHolder
 * 开发者：Lenovo
 * 开发时间：2019年4月22日上午10:47:50
 */
@Component
@Lazy(false)
public class DataSourceContextHolder {
	// 采用ThreadLocal 保存本地多数据源
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

	// 设置数据源类型
	public static void setDbType(String dbType) {
		contextHolder.set(dbType);
	}

	public static String getDbType() {
		return contextHolder.get();
	}

	public static void clearDbType() {
		contextHolder.remove();
	}

}
