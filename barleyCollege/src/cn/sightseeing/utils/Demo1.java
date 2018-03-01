package cn.sightseeing.utils;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class Demo1 {
	@Test
	public void fun1(){
		final ThreadLocal<String> tl=new ThreadLocal<String>();
		
		new Thread(){
			public void run(){
				tl.set("内部类");
			}
		}.start();
		try{
			Thread.sleep(1000);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println(tl.get());
	}
	
	@Test
	public void fun2(){
		Map<Thread,String> map=new HashMap<Thread,String>();
		map.put(Thread.currentThread(), "Hello");
		System.out.println(map.get(Thread.currentThread()));
		new Thread(){
			public void run(){
				System.out.println(Thread.currentThread().getName());
				System.out.println(Thread.currentThread());
			}
		}.start();
	}
	@Test
	public void add(){
		
	}
}
