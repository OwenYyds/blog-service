package com.owen;


import org.junit.jupiter.api.Test;

public class ThreadLocalTest {

	@Test
	public void test() {
		ThreadLocal t1 = new ThreadLocal();

		new Thread(() -> {
			t1.set("Thread1");
			System.out.println(Thread.currentThread().getName() + " " + t1.get());
		}, "Thread1").start();

		new Thread(() -> {
			t1.set("Thread2");
			System.out.println(Thread.currentThread().getName() + " " + t1.get());
		}, "Thread2").start();
	}
}
