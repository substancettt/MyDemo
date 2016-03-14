package com.wellshang.mytools;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class Consumer implements Runnable {

    private BlockingQueue<String> queue;
    private static final int      DEFAULT_RANGE_FOR_SLEEP = 1000;
    private Integer id = 0;
    private static Integer index = 0;

    public Consumer(BlockingQueue<String> queue) {
    	id = index++;
        this.queue = queue;
    }
 
    public void run() {
        System.out.println("�����������߳�" + id + "��");
        Random r = new Random();
        boolean isRunning = true;
        try {
            while (isRunning) {
                System.out.println("�������߳�" + id + "���Ӷ��л�ȡ����...");
                String data = queue.poll(2, TimeUnit.SECONDS);
                if (null != data) {
                    System.out.println("�������߳�" + id + "�õ����ݣ�" + data);
                    System.out.println("�������߳�" + id + "�����������ݣ�" + data);
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                } else {
                    isRunning = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("�˳��������߳�" + id + "��");
        }
    }
}