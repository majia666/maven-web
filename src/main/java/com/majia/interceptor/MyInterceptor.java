package com.majia.interceptor;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.alibaba.fastjson.JSONObject;
import com.majia.utils.LoggerUtil;

@ControllerAdvice
public class MyInterceptor implements HandlerInterceptor ,ResponseBodyAdvice<Object>{

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object object, Exception exception)
			throws Exception {
		// TODO Auto-generated method stub
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelAndView)
			throws Exception {
		// TODO Auto-generated method stub
		// 获取提交 ip
		String ip = getIp(request);

		String uri = request.getRequestURI();

		Object attribute = request.getAttribute("response");

		LoggerUtil.info("response ip: " + ip + ",uri:"+ uri + ",result: "+JSONObject.toJSONString(attribute));
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		// TODO Auto-generated method stub
		// 获取提交 ip
		String ip = getIp(request);

        // 请求方法
        String method = request.getMethod();

        //获取uri
        String uri = request.getRequestURI();

        Enumeration<String> paramKeys = request.getParameterNames();
      	Map<String, String> map = new HashMap<String, String>();
        while(paramKeys.hasMoreElements()) {
            String key = paramKeys.nextElement();
            String value = request.getParameter(key);
            map.put(key, value);
        }
        LoggerUtil.info("request ip: " + ip + ",uri:"+ uri + ",method: " + method + ",params: " + JSONObject.toJSONString(map));

		return true;
	}

	@Override
	public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
			Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
			ServerHttpResponse response) {
		// TODO Auto-generated method stub
		ServletServerHttpRequest req=(ServletServerHttpRequest)request;
		HttpServletRequest servletRequest = req.getServletRequest();
		servletRequest.setAttribute("response", body);
		return body;
	}

	// 获取请求ip
	private static String getIp(HttpServletRequest request) {
		// 获取提交 ip
		String comma = ",";
		String localhostIP = "0:0:0:0:0:0:0:1";
		String commonIP = "0.0.0.0.0.0.0.1%0";
		String ip = request.getHeader("x-forwarded-for");

		if (isNull(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (isNull(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (isNull(ip)) {
			ip = request.getHeader("X-Real-IP");
		}
		if (isNull(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (isNull(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (isNull(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.contains(comma)) {
			ip = ip.split(",")[0];
		}
		if (localhostIP.equals(ip) || commonIP.equals(ip)) {
			ip = "127.0.0.1";
		}
        return ip;
	}

	private static boolean isNull(String ip) {
		String unknown = "unknown";
		return null == ip || 0 == ip.length() || unknown.equalsIgnoreCase(ip);
	}
}
