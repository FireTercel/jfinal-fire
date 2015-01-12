package com.demo.common.kit;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.demo.common.reflect.model.Man;
import com.demo.common.reflect.model.Woman;


/**
 * @描述 类工具，该类为单例模式，用于对其他类进行一系列有趣的操作。
 * @author DONGYU
 *
 */
public class ClassKit {
	
	private static Constructor<?> constructor;
	private static final Class<?>[] DEFAULT_NULL_CLASS=new Class[0];
	private static final Object[] DEFAULT_NULL_OBJECT=new Object[0];
	
	
	private ClassKit(){
		
	}
	/**
	 * @单例模式 通过一个内部类进行实例化，保证生成单个实例。
	 * @author DONGYU
	 *
	 */
	private static class ClassKitFactory{
		public static ClassKit instance=new ClassKit();
	}
	/**
	 * @描述 获得单例对象的接口
	 * @return ClassKit实例
	 */
	public ClassKit getInstance(){
		return ClassKitFactory.instance;
	}
	/**
	 * @描述 根据类路径 构建一个实例对象 
	 * @param className
	 * @return
	 */
	public Object getClassInstance(String className) {
		Object object = null;
		try {
			Class<?> clazz = Class.forName(className);

			constructor = clazz.getConstructor(DEFAULT_NULL_CLASS);

			object = constructor.newInstance();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		return object;
	}
	
	/**
	 * @描述 测试方法
	 */
	public void init(){
		try {
			Class<?> clazz=Class.forName("com.demo.common.reflect.model.Man");
			println("PackageName: \t"+clazz.getPackage().getName());
			println("ClassName: \t"+clazz.getName());
			println("SimpleClassName: \t"+clazz.getSimpleName());
			println("ToString: \t"+clazz.toString());
			println(clazz.getCanonicalName());
			println(clazz.getClassLoader().toString());
			
			
			print("* ",50);
			Field[] fields=clazz.getFields();
			Field[] fieldDecls=clazz.getDeclaredFields();
			for (int i = 0; i < fields.length; i++) {
				Field field=fields[i];
				println("Field name:\t"+field.getName());
				println("Field type:\t"+field.getType().getName());
				/*
				 * 如：  PUBLIC:1 PRIVATE:2 PROTECTED:4 STATIC:8 FINAL:16
				 * 	   SYNCHRONIZED:32 VOLATILE:64 TRANSIEMT:128 NATIVE:256
				 * 	   INTERFACE:512 ABSTRACT:1024 STRICT:2048
				 */
				println("DeclField modifier:\t"+field.getModifiers());
			}
			print("- ",50);
			for (int i = 0; i < fieldDecls.length; i++) {
				Field field=fieldDecls[i];
				println("Field name:\t"+field.getName());
				println("Field type:\t"+field.getType().getName());
				println("DeclField modifier:\t"+field.getModifiers());
				
			}
			print("- ",50);
			Method[] methods=clazz.getDeclaredMethods();
			for (Method method : methods) {
				println("Method: "+method.getName());
				Annotation[] annos=	method.getAnnotations();
				
				for (Annotation anno : annos) {
					
					println("Anno: "+anno.getClass().getName());
					println("AnnoString: "+anno.toString());
				}
				if(annos.length>0){
					print("- ",50);
				}
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @描述 对指定对象obj 遍历所有属性 对public修饰的属性 修改其值为update
	 * @param obj
	 * @param className
	 * @param update
	 */
	public Object init(String className,String fieldName,Object update){
		Object instance=null;
		try {
			Class<?> clazz=Class.forName(className);
			instance=clazz.newInstance();
			Field[] fields=clazz.getDeclaredFields();
			for (Field field:fields) {
				println("Field name:\t"+field.getName());
				println("Field type:\t"+field.getType().getName());
				if(field.getModifiers()==1&&field.getName().equals(fieldName)){
					Object o=field.get(instance);
					println("修改前："+o);
					field.set(instance, update);
					println("修改后："+field.get(instance));
				}
				print(" -", 20);
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return instance;
	}
	
	/**
	 * @描述 通过调用method.invoke()方法执行对象的方法。
	 * @param className
	 * @param objects
	 */
	public Object init(String className,Object...objects){
		Object instance=null;
		try {
			Class<?> clazz=Class.forName(className);
			instance=clazz.newInstance();
			Method[] methods=clazz.getMethods();
			for (Method method : methods) {
				if(method.getName().equals("run"))
				method.invoke(instance, objects);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return instance;
	}
	
	/**
	 * @描述 根据类路径 方法名 
	 *     调用该类该方法
	 * @param model
	 * @param methodName
	 * @return
	 */
	public Object init(String model,String methodName){
		return init(model,methodName,DEFAULT_NULL_OBJECT);
	}
	
	/**
	 * @描述 根据类路径 方法名 方法参数 
	 *     调用该类该方法
	 * @param model
	 * @param methodName
	 * @param args
	 * @return
	 */
	public Object init(String className,String methodName,Object...args){
		Object instance=null;
		try {
			Class<?> clazz = Class.forName(className);
			instance=clazz.newInstance();
			Method[] methods=clazz.getMethods();
			for (Method method : methods) {
				if(method.getName().equals(methodName)){
					println("Method:\t"+method.getName());
					Class[] methodTypes=method.getParameterTypes();
					if(methodTypes.length!=args.length){
						println(method.getName()+" ：的形参数量与实参数量不一致！");
					}else{
						Class[] argTypes=new Class[args.length];
						for(int i=0;i<argTypes.length;i++){
							argTypes[i]=args[i].getClass();
						}
						boolean isEquals=true;
						for(int i=0;i<argTypes.length;i++){
							for(int j=0;j<methodTypes.length;j++){
								if(i==j){
									if(argTypes[i].getSimpleName().equals(methodTypes[j].getSimpleName()))
										break;
									else{
										isEquals=false;
										println(method.getName()+" ：的形参类型与实参类型不一致！");
										break;
									}
								}else
									continue;
							}
						}
						if(isEquals){
							method.invoke(instance, args);
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
		return instance;
		
	}
	
	
	
	/**
	 * @描述 封装了 `System.out.println()`方法。
	 * @param print
	 */
	public static void println(String print){
		System.out.println(print);
	}
	
	
	/**
	 * @描述 打印循环内容
	 * @param print 打印内容类型
	 * @param size 打印数量
	 */
	public static void print(String print,int size){
		for(int i=0;i<size;i++){
			print(print);
		}
		println("");
	}
	public static void print(String print){
		System.out.print(print);
	}
	
	public static void main(String [] args){
		ClassKit clazzKit=ClassKit.ClassKitFactory.instance;
		//clazzKit.init();
		Object[] obj=new Object[]{"test",20};
		Man man=(Man)clazzKit.init("com.demo.common.reflect.model.Man","setAll",obj);
		println(man.toString());
		
		
	}

}
