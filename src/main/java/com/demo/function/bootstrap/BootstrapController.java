package com.demo.function.bootstrap;

import cn.dreampie.routebind.ControllerKey;

import com.jfinal.core.Controller;

/**
 * 用于指向BootStrap例子的控制器。
 * @author Administrator
 *
 */
@ControllerKey(value="/bootstrap",path="/page/bootstrap")
public class BootstrapController extends Controller {
	
	public void index(){
		
		render("bootstrap.html");
	}
	
	public void theme(){
		render("theme.html");
	}

}
