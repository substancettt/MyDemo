package com.wellshang.mytools;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.wellshang.mytools.Producer;
import com.wellshang.mytools.Consumer;

public class BlockingQueueTest {

	@BeforeClass
	public void setUp() {
		System.out.println("setUp");
	}

	@Test(groups = { "groupA" })
	public void blockingQueueTest() {
		BlockingQueue<String> queue = new LinkedBlockingQueue<String>(10);

		Producer producer = new Producer(queue);
		Consumer consumer1 = new Consumer(queue);
		Consumer consumer2 = new Consumer(queue);

		ExecutorService service = Executors.newCachedThreadPool();

		service.execute(producer);
		service.execute(consumer1);
		service.execute(consumer2);

		try {
			Thread.sleep(10 * 1000);
			producer.stop();
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		service.shutdown();
	}

	@Test(groups = { "groupB" })
	public void slowTest() {
		System.out.println("slow test");
	}

	@Test(groups = { "groupB" })
	public void fastTest() {
		System.out.println("fast test");
	}
}