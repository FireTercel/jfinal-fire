package com.demo.common.kit;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

/**
 * 一个全局拦截器，用于输出HttpServletRequest获得的信息
 * @author DONGYU
 *
 */
public class HttpServletRequestInterceptor implements Interceptor {
	
	public void intercept(ActionInvocation ai) {
		new HttpServletRequestUtil(ai.getController().getRequest());
		ai.invoke();

	}

}
