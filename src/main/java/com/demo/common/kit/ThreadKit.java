package com.demo.common.kit;

public class ThreadKit {
	
	class SyncWait{
		
		//线程获得对象锁，但是进入阻塞队列，然后就没然后了。
		//其他线程也获取不到这个对象了
		void clueless()throws Exception{
			
			synchronized (this) {
				
				this.wait();
			}
			
		}
	}
	
	
	public static void main(String[] arg){
		ThreadKit threadKit=new ThreadKit();
		ThreadKit.SyncWait syncWait=threadKit.new SyncWait();
		AThread aThread=new AThread(syncWait);
		BThread bThread=new BThread(syncWait);
		aThread.run();
		bThread.run();
		
	}
	
	static class AThread implements Runnable{
		
		ThreadKit.SyncWait syncWait=null;
		
		public AThread(SyncWait syncWait){
			this.syncWait=syncWait;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					System.out.println(this);
					syncWait.clueless();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	static class BThread implements Runnable{
		
		ThreadKit.SyncWait syncWait=null;
		
		public BThread(SyncWait syncWait){
			this.syncWait=syncWait;
		}

		@Override
		public void run() {
			for (int i = 0; i < 10; i++) {
				try {
					System.out.println(this);
					syncWait.clueless();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	

}
