package com.demo.common.kit;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreemarkerKit {
	
	private static Configuration config=new Configuration();
	
	public Configuration getConfiguration(){
		return config;
	}
	
	static void init() throws Exception{
		config.setDirectoryForTemplateLoading(new File(System.getProperty("user.dir")+"/src/main/webapp/page/template"));
		config.setObjectWrapper(new DefaultObjectWrapper());
		Template temp=config.getTemplate("test.flt");
		Map<String,Object> root=new HashMap<>();
		root.put("user", "Fire");
		Map<String, Object> latest=new HashMap<>();
		latest.put("url", "www.baidu.com");
		latest.put("name", "Joe");
		root.put("latestProduct", latest);
		root.put("bool", false);
		
		Writer out=new OutputStreamWriter(System.out);
		temp.process(root, out);
		out.flush();
		
	}
	
	public static void main(String [] args) {
		//System.out.println(System.getProperty("user.dir"));   //该文件项目路径
		//System.out.println(FreemarkerKit.class.getResource("/")); //Class文件所在路径  
		try {
			FreemarkerKit.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
