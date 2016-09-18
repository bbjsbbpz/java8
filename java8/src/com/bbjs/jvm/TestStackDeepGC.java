package com.bbjs.jvm;

public class TestStackDeepGC {

	private static int count = 0;

	public static void recursion() {
		count++;
		recursion();
	}

	public static void recursion(long a, long b, long c) {
		long e = 1, f = 2, g = 3, h = 4, i = 5, k = 6, l = 7, m = 8, n = 9;
		count++;
		recursion(a, b, c);
	}

	public void localvar1() {
		int a = 0;
		System.out.println(a);
		int b = 0;
	}

	public void localvar2() {
		{
			int a = 0;
			System.out.println(a);
		}
		int b = 0;
	}

	public void localvarGc1() {
		byte[] a = new byte[6 * 1024 * 1024];
		System.gc();
	}

	public void localvarGc2() {
		byte[] a = new byte[6 * 1024 * 1024];
		a = null;
		System.gc();
	}

	public void localvarGc3() {
		{
			byte[] a = new byte[6 * 1024 * 1024];
		}
		System.gc();
	}

	public void localvarGc4() {
		{
			byte[] a = new byte[6 * 1024 * 1024];
		}
		int c = 10;
		System.gc();
	}

	public void localvarGc5() {
		localvarGc1();
		System.gc();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			// recursion();
			// recursion(1l,2l,3);
			//new TestStackDeep().localvar1();
			new TestStackDeepGC().localvarGc4();
		} catch (Throwable e) {
			System.out.println("deep of calling = " + count);
			e.printStackTrace();
		}
	}

}
