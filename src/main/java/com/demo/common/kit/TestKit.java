package com.demo.common.kit;

import org.junit.Test;

public class TestKit {
	public final int i;
	
	{
		i=10;
	}
	
	public void test(){
		Integer i1=Integer.valueOf(10);
		Integer i2=Integer.valueOf(10);
		System.out.println(i1==i2);
		Integer i3=new Integer(10);
		Integer i4=new Integer(10);
		System.out.println(i3==i4);
		String s1="JavaHome";
		String s2="Java";
		String s3="Home";
		String s4="Java"+"Home";
		System.out.println(s1==s4);
		String s11=new String("JavaHome");
		String s22=new String("Java");
		String s33=new String("Home");
		String s44=new String("Java"+"Home");
		System.out.println(s11==s44);
	}
	
	@Test
	public void test1(){
		Integer i1=127;
		Integer i2=127;
		Integer i3=-128;
		Integer i4=-128;
		System.out.println(i1==i2);
		System.out.println(i3==i4);
	}

}
