package com.demo.common.kit;

/**
 * 计算方法使用时间
 * @author DONGYU
 *
 */
public class ExecuteTimeKit {
	private static long start;
	private static long end;
	private static long result;
	
	
	public static long startTime(){
		//start=System.nanoTime();纳秒
		start=System.currentTimeMillis();
		System.out.println("开始时间："+start);
		return start;
	}
	
	public static long endTime(){
		//end=System.nanoTime();纳秒
		end=System.currentTimeMillis();
		System.out.println("结束时间："+end);
		return end;
	}
	
	public static void printExTime(){
		result=end-start;
		System.out.println("方法运行时间："+result+" 毫秒（ms）");
	}
	
	

}
