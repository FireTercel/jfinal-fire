package com.demo.function.index;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.dreampie.captcha.CaptchaRender;
import cn.dreampie.routebind.ControllerKey;
import cn.dreampie.shiro.core.SubjectKit;

import com.demo.function.blog.Blog;
import com.jfinal.core.Controller;

/**
 * IndexController
 */
@ControllerKey(value = "/", path = "/page/index")
public class IndexController extends Controller {

	static String indexView = "index.html";
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/*public void setSuccess() {
		setAttr("state", "success");
	}

	public void setSuccess(String name, Object value) {
		setSuccess();
		setAttr(name, value);
	}

	public void setSuccess(Map<String, Object> attrMap) {
		setSuccess();
		setAttrs(attrMap);
	}

	public void setError() {
		setAttr("state", "error");
	}

	public void setError(String name, Object value) {
		setError();
		setAttr(name, value);
	}

	public void setError(Map<String, Object> attrMap) {
		setError();
		setAttrs(attrMap);
	}*/

	/**
	 * 根目录
	 */
	public void index() {
		render(indexView);
	}

	/**
	 * 验证码
	 */
	public void captcha() {
		int width = 0, height = 0, minnum = 0, maxnum = 0, fontsize = 0, fontmin = 0, fontmax = 0;
		CaptchaRender captcha = new CaptchaRender();
		if (isParaExists("width")) {
			width = getParaToInt("width");
		}
		if (isParaExists("height")) {
			height = getParaToInt("height");
		}
		if (width > 0 && height > 0)
			captcha.setImgSize(width, height);
		if (isParaExists("minnum")) {
			minnum = getParaToInt("minnum");
		}
		if (isParaExists("maxnum")) {
			maxnum = getParaToInt("maxnum");
		}
		if (minnum > 0 && maxnum > 0)
			captcha.setFontNum(minnum, maxnum);
		if (isParaExists("fontsize")) {
			fontsize = getParaToInt("fontsize");
		}
		if (fontsize > 0)
			captcha.setFontSize(fontsize, fontsize);
		// 干扰线数量 默认0
		captcha.setLineNum(1);
		// 噪点数量 默认50
		captcha.setArtifactNum(20);
		// 使用字符 去掉0和o 避免难以确认
		captcha.setCode("123456789");
		// 验证码在session里的名字 默认 captcha,创建时间为：名字_time
		// captcha.setCaptchaName("captcha");
		// 验证码颜色 默认黑色
		// captcha.setDrawColor(new Color(255,0,0));
		// 背景干扰物颜色 默认灰
		// captcha.setDrawBgColor(new Color(0,0,0));
		// 背景色+透明度 前三位数字是rgb色，第四个数字是透明度 默认透明
		// captcha.setBgColor(new Color(225, 225, 0, 100));
		// 滤镜特效 默认随机特效 //曲面Curves //大理石纹Marble //弯折Double //颤动Wobble //扩散Diffuse
		captcha.setFilter(CaptchaRender.FilterFactory.Curves);
		// 随机色 默认黑验证码 灰背景元素
		captcha.setRandomColor(true);
		render(captcha);
	}

	public void authed() {
		setAttr("isAuthed", SubjectKit.isAuthed());
		render(indexView);
	}

	public void tosignin() {
		redirect("/");
	}

	public void signin() {
		render("_signin.ftl");
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
