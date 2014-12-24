package com.demo.common.config;

import cn.dreampie.log.Slf4jLogFactory;
import cn.dreampie.routebind.RouteBind;
import cn.dreampie.shiro.core.ShiroInterceptor;
import cn.dreampie.shiro.core.ShiroPlugin;
import cn.dreampie.shiro.freemarker.ShiroTags;
import cn.dreampie.sqlinxml.SqlInXmlPlugin;
import cn.dreampie.tablebind.SimpleNameStyles;
import cn.dreampie.tablebind.TableBindPlugin;
import cn.dreampie.web.interceptor.UrlInterceptor;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.wall.WallFilter;
import com.demo.common.resource.ResourceTags;
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
import com.jfinal.config.*;
import com.jfinal.core.JFinal;
import com.jfinal.ext.handler.ContextPathHandler;
import com.jfinal.ext.interceptor.SessionInViewInterceptor;
import com.jfinal.log.Logger;
import com.jfinal.plugin.activerecord.CaseInsensitiveContainerFactory;
import com.jfinal.plugin.activerecord.dialect.AnsiSqlDialect;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.plugin.ehcache.EhCachePlugin;
import com.jfinal.render.FreeMarkerRender;
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
		loadPropertyFile("application.properties");
		me.setDevMode(getPropertyToBoolean("devMode", false));
		Logger.setLoggerFactory(new Slf4jLogFactory());
		me.setViewType(ViewType.FREE_MARKER);
		//me.setError404View("error404.html");
		//me.setError500View("error500.html");
	}
	
	/**
	 * 配置路由
	 * 
	 * 原始：配置路由 第三个参数，最好加上去，因为它代表了view存放的位置。
	 * 		如果第三个参数省略是，路径默认和第一个参数相同。
	 * 
	 * 加入RouteBind插件：不需要在这里为每一个controller配置路由
	 * 					只需要在每一个controller中，
	 * 					配置@ControllerKey(value="/clothes",path="/page/clothes")
	 * 					就可以了。
	 */
	public void configRoute(Routes me) {
		
		this.routes=me;
		RouteBind routeBind=new RouteBind();
		
		me.add(routeBind);
		
		//me.add("/", IndexController.class, "/page/index");	// 第三个参数为该Controller的视图存放路径
		//me.add("/blog", BlogController.class,"/page/blog");			// 第三个参数省略时默认与第一个参数值相同，在此即为 "/blog"
		//me.add("/clothes",ClothesController.class,"/page/clothes");
		//me.add("/workmate", WorkmateController.class, "/page/workmate");
		//me.add("/bootstrap",BootstrapController.class,"/page/bootstrap");
	}
	
	/**
	 * 配置插件
	 * 
	 * 原始：配置插件，数据库连接池可以使用C3p0Plugin，
	 * 		也可以使用阿里巴巴的DruidPlugin；
	 * 直接使用ActiveRecordPlugin插件：
	 * 		需要使用arp.addMapping("blog", Blog.class);做映射；
	 * 加入TableBindPlugin插件：
	 * 		该插件封转了ActiveRecordPlugin的功能，
	 * 		直接在Model中加入@TableBind(tableName="blog")注解就可以了。
	 */
	public void configPlugin(Plugins me) {
		//配置C3p0数据库连接池插件
		//C3p0Plugin c3p0Plugin = new C3p0Plugin(getProperty("jdbcUrl"), getProperty("user"), getProperty("password").trim());
		//me.add(c3p0Plugin);
		
		//配置druid连接池
		DruidPlugin druidPlugin = new DruidPlugin(getProperty("db.default.url"), getProperty("db.default.user"), getProperty("db.default.password"), getProperty("db.default.driver"));
		// StatFilter提供JDBC层的统计信息
		druidPlugin.addFilter(new StatFilter());
		// WallFilter的功能是防御SQL注入攻击
		WallFilter wallFilter=new WallFilter();
		wallFilter.setDbType("h2");
		druidPlugin.addFilter(wallFilter);
		
		druidPlugin.setInitialSize(getPropertyToInt("db.default.poolInitialSize"));
		druidPlugin.setMaxPoolPreparedStatementPerConnectionSize(getPropertyToInt("db.default.poolMaxSize"));
		druidPlugin.setTimeBetweenConnectErrorMillis(getPropertyToInt("db.default.connectionTimeoutMillis"));
	    
		me.add(druidPlugin);
		
		// 配置ActiveRecord插件
		//ActiveRecordPlugin arp = new ActiveRecordPlugin(c3p0Plugin);
		//ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		//me.add(arp);
		//arp.addMapping("blog", Blog.class);	// 映射blog 表到 Blog模型
		//arp.addMapping("clothes", Clothes.class);// 映射clohtes 表到 Clothes模型
		//arp.addMapping("clothimage", Clothimage.class);
		//arp.addMapping("workmate", Workmate.class);
		
		//配置通过Model的Table注解，映射到数据库
		//通过TableBindPlugin中的start方法，实现了扫描注解为TableBind的model
		//调用了arp.addMapping(tableName, modelClass)方法，实现了ORM功能
		TableBindPlugin tableBindPlugin=new TableBindPlugin(druidPlugin,SimpleNameStyles.LOWER);
		//忽略字段大小写(Container集装箱、sensitive敏感的)
		tableBindPlugin.setContainerFactory(new CaseInsensitiveContainerFactory(true));
		tableBindPlugin.setShowSql(getPropertyToBoolean("devMode", false));
		//非mysql的数据库方言
		tableBindPlugin.setDialect(new AnsiSqlDialect());
		me.add(tableBindPlugin);
		
		
		//sql语句plugin
		me.add(new SqlInXmlPlugin());
		//ehcache缓存
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
		//添加shiro的过滤器到interceptor
		me.add(new ShiroInterceptor());
		me.add(new SessionInViewInterceptor());
		me.add(new UrlInterceptor());
	}
	
	/**
	 * 配置处理器
	 */
	public void configHandler(Handlers me) {
		me.add(new ContextPathHandler("contextPath"));
	}
	
	public void afterJFinalStart(){
		super.afterJFinalStart();
		FreeMarkerRender.setProperties(loadPropertyFile("freemarker.properties"));
		FreeMarkerRender.getConfiguration().setSharedVariable("shiro",new ShiroTags());
		FreeMarkerRender.getConfiguration().setSharedVariable("resource", new ResourceTags());
	}
	
	/**
	 * 建议使用 JFinal 手册推荐的方式启动项目
	 * 运行此 main 方法可以启动项目，此main方法可以放置在任意的Class类定义中，不一定要放于此
	 */
	public static void main(String[] args) {
		JFinal.start("src/main/webapp", 80, "/", 5);
	}
}
