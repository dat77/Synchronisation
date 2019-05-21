package com.gmail.sinchro;

public class Harbour {

	private String name;
	private boolean[] dock = new boolean[2]; // false = free dock

	public Harbour(String name) {
		this.name = name;
	}

	public Harbour() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int findFreeDock() {
		for (int i = 0; i < dock.length; i++) {
			if (dock[i] == false) {
				return i;
			}
		}
		return -1;
	}
	
	public synchronized int roadstead(Ship ship) {
		int freeDock;
		while((freeDock = findFreeDock())==-1) {
			try {
				System.out.println("Ship " + ship.getName() + " has stopped in the roads");
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		dock[freeDock] = true;
		System.out.println("Ship " + ship.getName() + " goes into the dock #"+freeDock);
		return freeDock;
	}
	
	public void unload(Ship ship, int freeDock) {
		for (Container container : ship.getCargo()) {
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (container!=null) {
				System.out.println("Dock #" + freeDock + " unloads " +  ship.getName() + " " + container.getDescription());
			}
		}
	}
	
	public synchronized void leaveDock(Ship ship, int freeDock) {
		System.out.println("Ship " + ship.getName() + " leaves the dock #"+freeDock);
		dock[freeDock] = false;
		notifyAll();
	}
	

}
