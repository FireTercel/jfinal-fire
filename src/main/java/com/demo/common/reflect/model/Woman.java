package com.demo.common.reflect.model;

import java.util.HashMap;
import java.util.Map;

import com.demo.common.kit.ClassKit;

public class Woman implements Person {
	public String name="Fire";
	
	private Map<String,Object> attrs;

	public Map<String, Object> getAttrsMap(){
		return new HashMap<String, Object>();
	}
	
	public void setAttr(String attr,Object value){
		attrs.put(attr, value);
	}
	
	public Object getAttr(String attr){
		return attrs.get(attr);
	}

	@Override
	public void run() {
		ClassKit.println("I am Woman!I am running!");
	}

	@Override
	public void say() {
		ClassKit.println("I am Woman!");
	}

	@Override
	public void reName() {
		this.name="Woman";
	}

}
