package controller;

import java.util.concurrent.Semaphore;

public class Transacao extends Thread {
	
	private int ThreadId;
	private Semaphore mutex;
	private double calc, bd;
	
	public Transacao (int ThreadId, Semaphore mutex) {
		this.ThreadId = ThreadId;
		this.mutex = mutex;
	}
	
	public void run() {
		calc();
	}
	
	private void calc() {
		bd = 1000;
		switch (ThreadId % 3) {
		case 0:
			calc = ((Math.random()*1001)+1000);
			transacaobd();
			gravabd();
		case 1:
			calc = ((Math.random()*801)+200);
			transacaobd();
			gravabd();
		case 2:
			calc = ((Math.random()*1001)+500);
			transacaobd();
			gravabd();
			}
	}
	
	private void transacaobd() {
		double time = calc / 1000;
		System.out.format("A Thread: " + ThreadId + " calculou por %@2f segundos. \n ", time);
		try {
			sleep((long) calc);
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void gravabd() {
		try {
			mutex.acquire();
			sleep((long) bd);
			System.out.println("A Thread: " + ThreadId + " gravou em " +(double)(bd/1000) + " segundos");
			}catch (InterruptedException e){
				e.printStackTrace();
			} finally {
				mutex.release();
			}
	}

}
