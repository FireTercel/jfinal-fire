package com.demo.common.kit;

import java.util.Enumeration;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;


public class HttpServletRequestKit{
	private static HttpServletRequest request;
	public HttpServletRequestKit(){}
	//拦截器中调用该构造器
	public HttpServletRequestKit(HttpServletRequest req){
		request=req;
		//初始化方法，把需要打印的内容添加这个方法中
		init();
	}
	
	public static void init(){
		//打印客户机信息
		printHttpInfo();
		//打印客户机请求头信息
		printHttpHeadInfo();
		
		printHttpReqParams();
	}
	
	public static void printHttpInfo(){
		String uri=request.getRequestURI();
		String rad=request.getRemoteAddr();
		String rh=request.getRemoteHost();
		String ru=request.getRemoteUser();
		int rp=request.getRemotePort();
		String cp=request.getContextPath();
		String la=request.getLocalAddr();
		String ce=request.getCharacterEncoding();
		String gm=request.getMethod();
		String qs=request.getQueryString();
		
		print("    ======   This will get 客户机信息  start ====  ");
		
		print("URI:"+uri);
		print("RemoteAddr:"+rad);
		print("RemoteHost:"+rh);
		print("RemoteUser:"+ru);
		print("RemotePort:"+rp);
		print("ContextPath:"+cp);
		print("LocalAddr:"+la);
		print("CharEncoding:"+ce);
		print("Method:"+gm);
		print("QueryString:"+qs);
		print("    ======   This will get 客户机信息  end ====  ");
	}
	
	public static void printHttpHeadInfo(){
		print("    ======   This will get 客户机请求头信息  start ====  ");
		print(""+request.getHeader("method"));
		Enumeration<String> e=request.getHeaderNames();
		while(e.hasMoreElements()){
			String name=e.nextElement();
			String value=request.getHeader(name);
			print(name+":"+value);
		}
		print("    ======   This will get 客户机请求头信息  end ====  ");
	}
	
	public static void printHttpReqParams(){
		print("    ======   This will print parameterMap ====  ");
		Map<String, String[]> parasMap = request.getParameterMap();
		if (parasMap==null) {
			print("parameterMap is null");
		}else {
			for (Entry<String,String[]> e : parasMap.entrySet()) {
				String paraKey=e.getKey();
				print(paraKey);
				
			}
		}
		
	}
	
	
	public static void print(String print){
		System.out.println(print);
	}

}
