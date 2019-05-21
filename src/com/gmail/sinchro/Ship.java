package com.gmail.sinchro;

public class Ship {
	
	private String name;
	private Container[] cargo = new Container[10];
	
	public Ship(String name) {
		this.name = name;
	}

	public Ship() {
		this.name = "unknown";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Container[] getCargo() {
		return cargo;
	}
	
	public int loadCargo(Container container) {
		for (int i = 0; i < cargo.length; i++) {
			if (cargo[i] == null) {
				cargo[i] = container;
				return i;
			}
		}
		return -1;
	}
	
	
	

}
