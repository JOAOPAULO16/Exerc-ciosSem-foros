package view;

import java.util.concurrent.Semaphore;

import controller.Transacao;

public class Main {
	
	public static void main(String[] args) {
		Semaphore mutex = new Semaphore(1);
		
		for (int ThreadId = 1; ThreadId < 22; ThreadId++) {
			Transacao transacao = new Transacao (ThreadId, mutex);
			transacao.start();
		}
	}

}
