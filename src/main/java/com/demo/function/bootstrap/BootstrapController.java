package com.demo.function.bootstrap;

import com.jfinal.core.Controller;

/**
 * 用于指向BootStrap例子的控制器。
 * @author Administrator
 *
 */
public class BootstrapController extends Controller {
	
	public void index(){
		
		render("bootstrap.html");
	}
	
	public void theme(){
		render("theme.html");
	}

}
