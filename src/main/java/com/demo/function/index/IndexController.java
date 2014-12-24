package com.demo.function.index;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.dreampie.routebind.ControllerKey;
import cn.dreampie.shiro.core.SubjectKit;

import com.demo.function.blog.Blog;
import com.jfinal.core.Controller;

/**
 * IndexController
 */
@ControllerKey(value="/",path="/page/index")
public class IndexController extends Controller {
	
	static String indexView="index.html";
	protected Logger logger=LoggerFactory.getLogger(getClass());
	
	public void setSuccess(){
		setAttr("state", "success");
	}
	
	public void setSuccess(String name,Object value){
		setSuccess();
		setAttr(name, value);
	}
	
	public void setSuccess(Map<String, Object> attrMap){
		setSuccess();
	    setAttrs(attrMap);
	}
	
	public void setError(){
		setAttr("state", "error");
	}
	
	public void setError(String name,Object value){
		setError();
		setAttr(name, value);
	}
	
	public void setError(Map<String, Object> attrMap){
		setError();
		setAttrs(attrMap);
	}
	
	/**
	 * 根目录
	 */
	public void index() {
		render(indexView);
	}
	
	public void authed(){
		setAttr("isAuthed", SubjectKit.isAuthed());
		render(indexView);
	}
	
	public void tosignin(){
		redirect("/");
	}

	/**
	 * 测试在 maven 下的 jstl 输出功能 1：确保 web.xml 内容的 app-app 标记中的servlet版本号至少为
	 * 2.5，内容如下： <web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	 * xmlns="http://java.sun.com/xml/ns/javaee"
	 * xmlns:web="http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	 * xsi:schemaLocation=
	 * "http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_2_5.xsd"
	 * id="WebApp_ID" version="2.5"> 2：确保 jsp 页面中引用了 jstl 标记库，内容如下： <%@ taglib
	 * uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 3：确保 jetty
	 * 下开发时引入了正确的依赖，详情在 pom.xml 中有注释说明
	 */
	public void jstl_test() {
		setAttr("blogList", Blog.me.find("select * from blog"));
		renderJsp("/jstl_test.jsp");
	}
}
