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
	
	public void tree(){
		//URL:http://www.js-css.cn/jscode/nav/nav23/
		//URL:http://bookshadow.com/weblog/2014/05/17/jquery-bootstrap-tree-list/
		render("tree.html");
	}
	
	public void school(){
		render("school.html");
	}

}
