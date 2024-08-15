package com.mypack;
 
class MyThread1 extends Thread {
	Object lock1;
	Object lock2;
	
	public MyThread1(Object lock1, Object lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}

	public void run () {
		synchronized (lock1) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lock2) {
				System.out.println("MyThread1..........run");
			}
		}
	}
}

class MyThread2 extends Thread {
	Object lock1;
	Object lock2;
	
	public MyThread2(Object lock1, Object lock2) {
		this.lock1 = lock1;
		this.lock2 = lock2;
	}
	
	public void run () {
		synchronized (lock2) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lock1) {
				System.out.println("MyThread2..........run");
			}
		}
	}
}

public class DeadLock {
	public static void main(String[] args) {
		Object lock1 = new Object();
		Object lock2 = new Object();
		new MyThread1(lock1, lock2).start();
		new MyThread2(lock1, lock2).start();
	}
}
