package com.demo.common.kit;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogFilter implements Filter {
	
	FilterConfig config;
	
	

	public FilterConfig getFilterConfig() {
		return config;
	}

	public void setFilterConfig(FilterConfig config) {
		this.config = config;
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		config=filterConfig;

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		
		ServletContext context=getFilterConfig().getServletContext();
		long bef=System.currentTimeMillis();
		chain.doFilter(request, response);
		long aft=System.currentTimeMillis();
		System.out.println("自定义过滤器： "+request.getRequestURI()+" ; 时间："+(aft-bef)+" ms");
		context.log("Request to "+request.getRequestURI()+" ; 时间："+(aft-bef)+" ms");

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
