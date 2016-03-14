package com.wellshang.mytools;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Producer implements Runnable {

    private volatile boolean isRunning = true;
    private BlockingQueue<String> queue;
    private static AtomicInteger  count = new AtomicInteger();
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;
    private Integer id = 0;
    private static Integer index = 0;

    public Producer(BlockingQueue<String> queue) {
    	id = index++;
        this.queue = queue;
    }
 
    public void run() {
        String data = null;
        Random r = new Random();
 
        System.out.println("�����������߳�" + id + "��");
        try {
            while (isRunning) {
                System.out.println("�������߳�" + id + "������������...");
                Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
 
                data = "data:" + count.incrementAndGet();
                System.out.println("�������߳�" + id + "�����ݣ�" + data + "�������...");
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.out.println("�������߳�" + id + "��������ʧ�ܣ�" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("�˳��������߳�" + id + "��");
        }
    }
 
    public void stop() {
        isRunning = false;
    }
}