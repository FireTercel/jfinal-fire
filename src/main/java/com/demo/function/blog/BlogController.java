package com.demo.function.blog;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.dreampie.routebind.ControllerKey;

import com.demo.common.kit.HttpServletRequestInterceptor;
import com.jfinal.aop.Before;
import com.jfinal.core.Controller;

/**
 * BlogController</br>
 * 所有 sql 与业务逻辑写在 Model 或 Service 中</br>
 * 不要写在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@ControllerKey(value="/blog",path="/page/blog")
@Before({BlogInterceptor.class,HttpServletRequestInterceptor.class})
public class BlogController extends Controller {
	
	protected Logger logger=LoggerFactory.getLogger(getClass());
	
	public void index() {
		
		setAttr("blogPage", Blog.me.paginate(getParaToInt(0, 1), 10));
		logger.warn("输出测试！");
		render("blog.html");
	}
	
	public void add() {
	}
	
	@Before(BlogValidator.class)
	public void save() {
		getModel(Blog.class).save();
		redirect("/blog");
	}
	
	public void edit() {
		
		setAttr("blog", Blog.me.findById(getParaToInt()));
	}
	
	@Before(BlogValidator.class)
	public void update() {
		
		getModel(Blog.class).update();
		redirect("/blog");
	}
	
	public void delete() {
		Blog.me.deleteById(getParaToInt());
		redirect("/blog");
	}
}


