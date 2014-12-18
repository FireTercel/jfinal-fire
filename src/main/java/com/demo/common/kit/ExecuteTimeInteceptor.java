package com.demo.common.kit;

import com.jfinal.aop.Interceptor;
import com.jfinal.core.ActionInvocation;

public class ExecuteTimeInteceptor implements Interceptor {

	@Override
	public void intercept(ActionInvocation ai) {
		
		ExecuteTimeKit.startTime();
		ai.invoke();
		ExecuteTimeKit.endTime();
		ExecuteTimeKit.printExTime();

	}

}
