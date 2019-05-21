package com.gmail.sinchro;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		Harbour harbour = new Harbour("Pirl harbour");
		Ship ship1 = new Ship("Pequod");
		Ship ship2 = new Ship("Jungfrau");
		Ship ship3 = new Ship("Rachel");
		for (int i = 0; i < 10; i++) {
			ship1.loadCargo(new Container("Cargo " +(i+1)));
			ship2.loadCargo(new Container("Cargo " +(i+1)));
			ship3.loadCargo(new Container("Cargo " +(i+1)));
		}
		
		Thread thread1 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(ship1.getName() + " has come to the " + harbour.getName());
				int freeDock = harbour.roadstead(ship1);
				harbour.unload(ship1, freeDock);
				harbour.leaveDock(ship1, freeDock);
			}
		});

		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(ship2.getName() + " has come to the " + harbour.getName());
				int freeDock = harbour.roadstead(ship2);
				harbour.unload(ship2, freeDock);
				harbour.leaveDock(ship2, freeDock);
			}
		});
		
		Thread thread3 = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(ship3.getName() + " has come to the " + harbour.getName());
				int freeDock = harbour.roadstead(ship3);
				harbour.unload(ship3, freeDock);
				harbour.leaveDock(ship3, freeDock);
			}
		});
		
		thread1.start();
		thread2.start();
		thread3.start();

	}

}
