package com.demo.common.freemarker;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import freemarker.core.Environment;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

public class UpperDirective implements TemplateDirectiveModel {
	
	/**
	 * @描述 获得标签的参数名为name的值
	 * @param params
	 * @return value
	 */
	String getName(Map params){
		try {
			return getParam(params,"name");
		} catch (TemplateModelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * @描述 获得标签的参数名为name的值的具体实现
	 * @param params
	 * @param name
	 * @return
	 * @throws TemplateModelException 
	 */
	protected String getParam(Map params,String name) throws TemplateModelException {
		Object value=params.get(name);
		if (value==null) {
			throw new TemplateModelException("标签的参数名称无效,参数名称应该为："+name);
		}
		if (value instanceof SimpleScalar) {
			return ((SimpleScalar)value).getAsString();
		}
		return null;
	}
	/**
	 * @描述 判断参数不为空
	 * @param params
	 * @throws TemplateModelException
	 */
	protected void verifyParameters(Map params)throws TemplateModelException {
		if (params.isEmpty()) {
			throw new TemplateModelException("标签的参数不能为空！");
		}
	}
	
	/**
	 * @描述 判断参数值是否满足条件，true则显示，false则不显示
	 * @param bool
	 * @return
	 */
	protected boolean showTagBody(String bool) {
		return bool.equals("123");
	}
	
	/**
	 * @描述 执行主要方法体
	 * @param env
	 * @param body
	 * @throws IOException
	 * @throws TemplateException
	 */
	protected void renderBody(Environment env,TemplateDirectiveBody body)throws IOException,TemplateException {
		if (body!=null) {
			body.render(new UpperCaseFilterWriter(env.getOut()));
		}else {
			throw new RuntimeException("missing body");
		}
	}
	/**
	 * @描述 执行主要方法体
	 * @param env
	 * @param params
	 * @param body
	 * @throws IOException
	 * @throws TemplateException
	 */
	public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
	    boolean show = showTagBody(getName(params));
	    if (show) {
	      renderBody(env, body);
	    }
	  }
	
	/**
	 * @描述 主要方法体
	 */
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		verifyParameters(params);
		if(loopVars.length !=0){
			throw new TemplateModelException("This directive doesn't allow loop variables.");
		}
		render(env, params, body);
	}
	
	private static class UpperCaseFilterWriter extends Writer{
		private final Writer out;
		UpperCaseFilterWriter(Writer out){
			this.out=out;
		}
		@Override
		public void write(char[] cbuf, int off, int len) throws IOException {
			char[] transformedCbuf=new char[len];
			for (int i = 0; i < len; i++) {
				transformedCbuf[i]=Character.toUpperCase(cbuf[i+off]);
			}
			out.write(transformedCbuf);
		}
		@Override
		public void flush() throws IOException {
			out.flush();
		}
		@Override
		public void close() throws IOException {
			out.close();
		}
		
	}


}
