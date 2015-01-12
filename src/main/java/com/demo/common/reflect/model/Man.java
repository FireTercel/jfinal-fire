package com.demo.common.reflect.model;

import java.util.HashMap;
import java.util.Map;

import com.demo.common.kit.ClassKit;

public class Man implements Person {
	
	public String name="Fire";
	public Integer age=25;
	
	private Map<String,Object> attrs=getAttrsMap();

	public Map<String, Object> getAttrsMap(){
		return new HashMap<String, Object>();
	}
	
	public void setAttr(String attr,Object value){
		attrs.put(attr, value);
	}
	
	public Object getAttr(String attr){
		return attrs.get(attr);
	}
	
	public void init(){
		this.setAttr("name", "Fire");
		this.setAttr("age", 10);
	}
	
	public void setAll(String name,Integer age){
		this.name=name;
		this.age=age;
	}
	public void setAll(String name,String age){
		this.name=name;
	}
	
	public String toString(){
		return "name:"+name+"\t age:"+age;
	}
	
	@Override
	public void run() {
		ClassKit.println("I am Man!I am running!");
	}

	@Override
	public void say() {
		ClassKit.println("I am Man!");
	}

	@Override
	public void reName() {
		this.name="Man";
		
	}

}
