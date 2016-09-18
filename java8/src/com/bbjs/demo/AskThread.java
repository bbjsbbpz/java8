package com.bbjs.demo;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class AskThread implements Runnable {

	CompletableFuture<Integer> re = null;

	public AskThread(CompletableFuture<Integer> re) {
		super();
		this.re = re;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		int myRe = 0;
		try {
			myRe = re.get() * re.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(myRe);
		
	}
	
	public static Integer calc(Integer para){
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return para*para;
	}
	
	public static void main(String[] args) throws InterruptedException {
		final CompletableFuture<Integer> future = new CompletableFuture<>();
		new Thread(new AskThread(future)).start();
		Thread.sleep(1000);
		future.complete(60);
		
		final CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->calc(50));
		try {
			System.out.println(future2.get());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//-Xms512m -Xmx1024m -XX:+PrintGCDetails

}
