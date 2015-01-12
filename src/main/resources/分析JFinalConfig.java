1、入口->JFinalFilter 位于 web.xml 实现了 javax.servlet.Filter 接口
	属性： 	Handler、
			Encoding、
			JfinalConfig、
			Constants、
			Jfinal、
			Logger 
		等。
	方法：init() 
			（1）
			//从web.xml文件中，根据configClass查找继承 JFinalConfig 的自定义类
			CreateJFinalConfig(filterConfig.getInitParameter("configClass"));
			->用于初始化 JfinalConfig 失败则抛出异常
			（2）
			//jfinalConfig在上面已经进行初始化。方法返回Boolean判断初始化成功否
			Jfinal.init(jfinalConfig, filterConfig.getServletContext())
			->进入该方法，可以看到：
				<1>
				This.servletContext = servletContext;
				<2>
				This.contextPath = servletContext.getContextPath();
				<3>
				InitPathUtil();
					->获取项目相对路径
				<4>
				Config.configJFinal(jfinalConfig);
					->执行了自定义 DemoConfig 的 7 个方法
						其中
						JfinalConfig.configConstant(constants);				
						InitLoggerFactory();
							->用于初始化日志工具
						JfinalConfig.configRoute(routes);
						JfinalConfig.configPlugin(plugins);					
						StartPlugins();	// very important!!!
							->用于启动插件
						JfinalConfig.configInterceptor(interceptors);
						JfinalConfig.configHandler(handlers);
					->其他 5 个方法用于给属性 	Constants
												Routes
												Plugins
												Interceptors
												Handlers
						赋值。
				<5>
				Constants = Config.getConstants();
				->属性 Constants 用于配置常量 下面的几个方法需要用到加载的常量
				<6>
				InitActionMapping();
				->这个方法重要的地方，在于里面的 BuildActionMapping() 方法。
					涉及到 Action 的映射。一者加载 Routes 二者加载 Interceptors

				<7>
				InitHandler();
				->用于初始化处理器 （暂时没有怎么使用过，需进一步了解）
				<8>
				InitRender();
				->这个方法重要的地方，在于里面的 
					RenderFactory.init(constants, servletContext) 方法。
					涉及到多种视图的初始化，例如：
						InitFreeMarkerRender(servletContext);
						InitVelocityRender(servletContext);
						InitJspRender(servletContext);
						InitFileRender(servletContext);
					等。

				<9>
				InitOreillyCos();
				->初始化文件上传的相关设置
					涉及到的类有	MultipartRequest
									OreillyCos
					等。
				<10>
				InitI18n();
				->初始化国际化相关信息 
				<11>
				InitTokenManager();
				->初始化 Token 信息，如果不设置 setTokenCache 
					则默认使用 HttpSession 生成 token
			(3)
			Handler=jfinal.getHandler();

			(4)
			Constants=Config.getConstants();

			(5)
			Encoding=constants.getEncoding();

			(6)
			JfinalConfig.afterJFinalStart();
	方法：doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			(1)
			HttpServletRequest request = (HttpServletRequest)req;
			HttpServletResponse response = (HttpServletResponse)res;
			(2)
			request.setCharacterEncoding(Encoding);
			(3)
			String target = request.getRequestURI();
			if (contextPathLength != 0)
			target = target.substring(contextPathLength);
			(4)
			boolean[] isHandled = {false};
			try {
				handler.handle(target, request, response, isHandled);
			}
			catch (Exception e) {
				if (log.isErrorEnabled()) {
					String qs = request.getQueryString();
					log.error(qs == null ? target : target + "?" + qs, e);
				}
			}
			
			if (isHandled[0] == false)
				chain.doFilter(request, response);
			->该方法会让拦截器进入下一个方法。
	方法：destroy()
			->生命周期完结。
			
完成 JFinalConfig 中对项目的一系列加载。

