package com.bbjs.jvm;

public class HeapAlloc {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		p("maxMemory=");
		pln(Runtime.getRuntime().maxMemory()+" bytes");
		p("free mem=");
		pln(Runtime.getRuntime().freeMemory()+" bytes");
		p("total mem=");
		pln(Runtime.getRuntime().totalMemory()+" bytes");
		
		byte[] b = new byte[1*1024*1024];
		pln("allocate 1M Space to Array.");
		
		p("maxMemory=");
		pln(Runtime.getRuntime().maxMemory()+" bytes");
		p("free mem=");
		pln(Runtime.getRuntime().freeMemory()+" bytes");
		p("total mem=");
		pln(Runtime.getRuntime().totalMemory()+" bytes");
		
		b = new byte[4*1024*1024];
		pln("allocate 4M Space to Array.");
		
		p("maxMemory=");
		pln(Runtime.getRuntime().maxMemory()+" bytes");
		p("free mem=");
		pln(Runtime.getRuntime().freeMemory()+" bytes");
		p("total mem=");
		pln(Runtime.getRuntime().totalMemory()+" bytes");
		
		
	}
	
	public static void p(Object object){
		System.out.print(object);
	}
	
	public static void pln(Object object){
		System.out.println(object);
	}

}
