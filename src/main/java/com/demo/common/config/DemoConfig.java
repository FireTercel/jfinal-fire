package com.demo.common.config;

import cn.dreampie.shiro.core.ShiroPlugin;

import com.demo.common.shiro.MyJdbcAuthzService;
import com.demo.function.blog.Blog;
import com.demo.function.blog.BlogController;
import com.demo.function.bootstrap.BootstrapController;
import com.demo.function.clothes.Clothes;
import com.demo.function.clothes.ClothesController;
import com.demo.function.clothes.Clothimage;
import com.demo.function.index.IndexController;
import com.demo.function.workmate.Workmate;
import com.demo.function.workmate.WorkmateController;
import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.c3p0.C3p0Plugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.ViewType;

/**
 * API引导式配置
 */
public class DemoConfig extends JFinalConfig {
	
	/**
	 * 供Shiro插件使用。
	 */
	Routes routes;

	/**
	 * 配置常量
	 */
	public void configConstant(Constants me) {
		// 加载少量必要配置，随后可用getProperty(...)获取值
		loadPropertyFile("a_little_config.txt");
		me.setDevMode(getPropertyToBoolean("devMode", false));
		me.setViewType(ViewType.FREE_MARKER);
		//me.setError404View("error404.html");
		//me.setError500View("error500.html");
	}
	
	/**
	 * 配置路由 第三个参数，最好加上去，因为它代表了view存放的位置。
	 * 如果第三个参数省略是，路径默认和第一个参数相同。
	 */
	public void configRoute(Routes me) {
		
		this.routes=me;
		
		me.add("/", IndexController.class, "/page/index");	// 第三个参数为该Controller的视图存放路径
		me.add("/blog", BlogController.class,"/page/blog");			// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
		me.add("/clothes",ClothesController.class,"/page/clothes");
		me.add("/workmate", WorkmateController.class, "/page/workmate");
		me.add("/bootstrap",BootstrapController.class,"/page/bootstrap");
	}
	
	/**
	 * 配置插件
	 */
	public void configPlugin(Plugins me) {
		// 配置C3p0数据库连接池插件
		C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		me.add(c3p0Plugin);
		
		// 配置ActiveRecord插件
		ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		me.add(arp);
		arp.addMapping("blog", Blog.class);	// 映射blog 表到 Blog模型
		arp.addMapping("clothes", Clothes.class);// 映射clohtes 表到 Clothes模型
		arp.addMapping("clothimage", Clothimage.class);
		arp.addMapping("workmate", Workmate.class);
		
		me.add(new EhCachePlugin());
		//shiro权限框架
	    me.add(new ShiroPlugin(routes, new MyJdbcAuthzService()));
	}
	
	/**
	 * 配置全局拦截器
	 */
	public void configInterceptor(Interceptors me) {
		//方法执行时间
		//me.add(new ExecuteTimeInteceptor());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("contextPath"));
	}
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 5);
	}
}
