package com.demo.common.kit;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

public class HttpServletRequestUtil{
	private static HttpServletRequest request;
	public HttpServletRequestUtil(){}
	//拦截器中调用该构造器
	public HttpServletRequestUtil(HttpServletRequest request){
		this.request=request;
		//初始化方法，把需要打印的内容添加这个方法中
		init();
	}
	
	public static void init(){
		//打印客户机信息
		printHttpInfo();
		//打印客户机请求头信息
		printHttpHeadInfo();
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
		
		System.out.println("    ======   This will get 客户机信息  start ====  ");
		
		System.out.println("URI:"+uri);
		System.out.println("RemoteAddr:"+rad);
		System.out.println("RemoteHost:"+rh);
		System.out.println("RemoteUser:"+ru);
		System.out.println("RemotePort:"+rp);
		System.out.println("ContextPath:"+cp);
		System.out.println("LocalAddr:"+la);
		System.out.println("CharEncoding:"+ce);
		System.out.println("Method:"+gm);
		System.out.println("QueryString:"+qs);
		System.out.println("    ======   This will get 客户机信息  end ====  ");
	}
	
	public static void printHttpHeadInfo(){
		System.out.println("    ======   This will get 客户机请求头信息  start ====  ");
		System.out.println(""+request.getHeader("method"));
		Enumeration<String> e=request.getHeaderNames();
		while(e.hasMoreElements()){
			String name=e.nextElement();
			String value=request.getHeader(name);
			System.out.println(name+":"+value);
		}
		System.out.println("    ======   This will get 客户机请求头信息  end ====  ");
	}

}
