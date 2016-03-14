package com.wellshang.thread;

class Bank {
	private int sum;

	private int codes;
	
	public int getCodes() {
		return codes;
	}

	public void setCodes(int codes) {
		this.codes = codes;
	}

	Object obj = new Object();
	public void add(int n) {
		synchronized (obj) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sum = sum + n;
			System.out.println("sum = " + sum);
		}
	}
	
	public synchronized void Add(int n) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			sum = sum + n;
			System.out.println("sum = " + sum);
		}
}

public class Customer implements Runnable {
	private Bank b = new Bank();
	
	private String info;

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	String getCustomerInfo() {
		return "VIP Customer";
	}

	public void run() {
		b.setCodes(999);
		System.out.println("Codes: " + b.getCodes());
		
		setInfo(getCustomerInfo());
		System.out.println("Customer info: " + getInfo());
		
		for (int i = 0; i < 3; i++) {
			b.Add(100);
		}
		
		System.out.println("Done!");
	}
}
