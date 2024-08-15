package com.mypack;

import java.util.ArrayList;
import java.util.List;

class Producer extends Thread {
	List<Integer> list;
	
	public Producer(List<Integer> list) {
		this.list = list;
	}

	@Override
	public void run() {
		int i = 0;
		while(i<50) {
			synchronized (list) {
				while (list.size() == 5) {
					try {
						System.out.println("Waiting...Size 5");
						list.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				list.add(i);
				System.out.println("Added..." + i);
				i++;
				list.notify();
			}
		}
	}
}

class Consumer extends Thread {
	List<Integer> list;
	
	public Consumer(List<Integer> list) {
		this.list = list;
	}
	
	@Override
	public void run() {
		int i = 0;
		while(i<50) {
			synchronized (list) {
				while (list.size() == 0) {
					try {
						System.out.println("Waiting...Size 0");
						list.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				
				System.out.println("Removed..." + list.remove(0));
				i++;
				list.notify();
			}
		}
	}
}

public class ProducerConsumer {
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<>();
		new Producer(list).start();
		new Consumer(list).start();
	}

}
