package com.bbjs.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.IntConsumer;
import java.util.stream.IntStream;

public class MainClass {

	static int[] arr = { 1, 2, 3, 4, 33, 55, 35, 5, 6, 7, 8, 9 };

	public static boolean isPrime(int number) {
		int tmp = number;
		if (tmp < 2) {
			return false;
		}
		for (int i = 2; Math.sqrt(tmp) >= i; i++) {
			if (tmp % i == 0)
				return false;
		}
		return true;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Thread(() -> System.out.println("这是一个java8的小例子,可以使用lambda表达式")).start();
		Arrays.stream(arr).forEach(System.out::print);
		Arrays.stream(arr).forEach((x) -> System.out.print("," + x));

		IntConsumer outprintln = System.out::println;
		IntConsumer errprintln = System.err::println;
		// Arrays.stream(arr).forEach(outprintln.andThen(errprintln));
		IntStream.range(1, 100000).filter(MainClass::isPrime).count();
		IntStream.range(1, 1000000).parallel().filter(MainClass::isPrime).count();
		System.out.println();
		List<Student> ss = new ArrayList<Student>();
		for (int i = 1; i < 10; i++) {
			Student student = new Student("student" + i, 80 + i);
			ss.add(student);
		}
		double ave = ss.stream().mapToDouble(s -> s.getScore()).average().getAsDouble();
		System.out.println(ave);
		Arrays.parallelSort(arr);
		Arrays.stream(arr).forEach(System.out::println);

		List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");
		Collections.sort(names, (a, b) -> b.compareTo(a));
		for (String name : names) {
			System.out.println(name);
		}
		names.forEach((x) -> System.out.print(x + ","));

		names.stream().sorted().forEach((x) -> System.out.print(x + " "));

		Map<Integer, String> map = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			map.putIfAbsent(i, "value" + i);
		}

		map.forEach((id, val) -> System.out.println(id + ":" + val));

		map.computeIfPresent(3, (num, val) -> val + num);
		map.get(3); // val33

		map.computeIfPresent(9, (num, val) -> null);
		map.containsKey(9); // false

		map.computeIfAbsent(23, num -> "val" + num);
		map.containsKey(23); // true

		map.computeIfAbsent(3, num -> "bam");
		map.get(3); // val33

		int max = 1000000;
		List<String> values = new ArrayList<>(max);
		for (int i = 0; i < max; i++) {
			UUID uuid = UUID.randomUUID();
			values.add(uuid.toString());
		}
		long t0 = System.nanoTime();

		long count = values.stream().sorted().count();
		System.out.println(count);

		long t1 = System.nanoTime();

		long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("sequential sort took: %d ms", millis));

		t0 = System.nanoTime();

		count = values.parallelStream().sorted().count();
		System.out.println(count);

		t1 = System.nanoTime();

		millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.println(String.format("parallel sort took: %d ms", millis));

		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");

		stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
		stringCollection.stream().sorted().filter((s) -> s.startsWith("b")).forEach(System.out::println);
		stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a))
				.forEach(System.out::println);
		boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));

		System.out.println(anyStartsWithA); // true

		boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));

		System.out.println(allStartsWithA); // false

		boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));

		System.out.println(noneStartsWithZ); // true

		long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();

		System.out.println(startsWithB); // 3

		Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);
		// http://www.jb51.net/article/48304.htm
		// values.forEach((x)->System.out.print(x+","));
	}
	// -XX:+PrintGCDetails
	// Stream 的创建需要指定一个数据源，比如 java.util.Collection的子类，List或者Set，
	// Map不支持。Stream的操作可以串行执行或者并行执行。
}
