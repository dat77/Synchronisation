package com.gmail.sinchro;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		Harbour harbour = new Harbour("Pirl harbour");
		Ship ship1 = new Ship("Pequod");
		Ship ship2 = new Ship("Jungfrau");
		Ship ship3 = new Ship("Rachel");
		Random rn = new Random();
		for (int i = 0; i < 10; i++) {
			ship1.loadCargo(new Container(String.format("%10h", rn.nextInt())));
			ship2.loadCargo(new Container(String.format("%10h", rn.nextInt())));
			ship3.loadCargo(new Container(String.format("%10h", rn.nextInt())));
		}
		
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				harbour.roadsteadOrUnload(ship1);
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				harbour.roadsteadOrUnload(ship2);
			}
		});
		
		Thread thread3 = new Thread(new Runnable() {
			@Override
			public void run() {
				harbour.roadsteadOrUnload(ship3);
			}
		});
		
		thread1.start();
		thread2.start();
		thread3.start();

	}

}
