package com.majia.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class LoggerUtil {
	private static Logger logger = null;
	private static Logger loggerWithClass;
	private static Object lock = new Object();
	private static Map<Class , Logger> cls_logger_mapping = new ConcurrentHashMap<Class, Logger>();
	static {
		logger = LoggerFactory.getLogger(LoggerUtil.class);
	}


	public static void info(Object obj) {
		logger.info(obj==null?"null":obj.toString());
	}

	public static void error(Object obj) {
		logger.error(obj==null?"null":obj.toString());
	}

	public static void info(Class<?> clazz,String message){
		if(cls_logger_mapping.get(clazz) == null){
			synchronized (lock) {
				loggerWithClass = LoggerFactory.getLogger(clazz);
				cls_logger_mapping.put(clazz, loggerWithClass);
			}
		}
		loggerWithClass.info(message==null?"null":message);
	}

	public static void error(Class<?> clazz,String message){

		if(cls_logger_mapping.get(clazz) == null){
			synchronized (lock) {
				loggerWithClass = LoggerFactory.getLogger(clazz);
				cls_logger_mapping.put(clazz, loggerWithClass);
			}
		}
		loggerWithClass.error(message==null?"null":message);
	}


}
