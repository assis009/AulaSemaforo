package controller;

import java.util.concurrent.Semaphore;

public class ThreadCarro extends Thread {

	private int idCarro;
	private static int posChegada;
	private static int posSaida;
	private Semaphore semaforo;

	public ThreadCarro(int idCarro, Semaphore semaforo) {
		this.idCarro = idCarro;
		this.semaforo = semaforo;
	}

	public void run() {
		carroAndando();
		// ----Inicio da seção critica ----
		try {
			semaforo.acquire();
			carroEstacionando();
		} catch (InterruptedException e) {

			e.printStackTrace();
		} finally {
			semaforo.release();
			// ---- Fim da seção critica ----
			carroSaindo();

		}

	}

	private void carroAndando() {
		int distanciaTotal = (int) ((Math.random() * 1001) + 1000);
		int distanciaPercorrida = 0;
		int deslocamento = 100;
		int tempo = 1000;

		while (distanciaPercorrida < distanciaTotal) {

			distanciaPercorrida += deslocamento;

			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("#" + idCarro + " já andou " + distanciaPercorrida);
		}

		posChegada++;
		System.out.println("#" + idCarro + " foi o " + posChegada + "o. a chegar");

	}

	private void carroEstacionando() {
		System.out.println("#" + idCarro + " estacionou");
		int tempo = (int) ((Math.random() * 2001) + 1000);
		try {
			sleep(tempo);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void carroSaindo() {
		posSaida++;
		System.out.println("#" + idCarro + " foi o " + posSaida + "o. a sair");

	}

}
