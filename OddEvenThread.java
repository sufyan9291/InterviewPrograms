package mypack;

class OddThread extends Thread{
	public OddThread(Counter c) {
		super();
		this.c = c;
	}
	Counter c;
	public void run(){
		while (true) {
			synchronized(c){
				while (c.count%2==0) {
					try {
						c.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(c.count);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c.count++;
				c.notify();
			}
			
			
			
		}
	}
}

class EvenThread extends Thread{
	public EvenThread(Counter c) {
		super();
		this.c = c;
	}
	Counter c;
	public void run(){
		while (true) {
			synchronized(c){
				while (c.count%2 != 0) {
					try {
						c.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println(c.count);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				c.count++;
				c.notify();
			}
			
			
			
		}
	}
}

class Counter {
	int count=1;
}

class OddEvenThread {
	public static void main(String[] args) {
		Counter c = new Counter();
		new EvenThread(c).start();
		new OddThread(c).start();
	}
}

