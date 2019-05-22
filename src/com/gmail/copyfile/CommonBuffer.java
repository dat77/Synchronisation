package com.gmail.copyfile;

public class CommonBuffer {
	
	private Buffer buffer = new Buffer();
	private boolean bufferReady = false;
	private boolean terminate = false;
	
	public CommonBuffer() {
	}
	
	public synchronized Buffer getBuffer() {
		while (!bufferReady) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		byte[] tmpBytes = new byte[Buffer.BUFFER_SIZE];
		System.arraycopy(this.buffer.getBuffer(), 0, tmpBytes, 0, this.buffer.getBytesInBuffer());
		Buffer buffer = new Buffer();
		buffer.setBuffer(tmpBytes);
		buffer.setBytesInBuffer(this.buffer.getBytesInBuffer());
		bufferReady = false;
		notify();
		return buffer;
	}
	
	public synchronized void setBuffer(Buffer buffer) {
		while (bufferReady) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		byte[] tmpBytes = new byte[Buffer.BUFFER_SIZE];
		System.arraycopy(buffer.getBuffer(), 0, tmpBytes, 0, buffer.getBytesInBuffer());
		this.buffer.setBuffer(tmpBytes);
		this.buffer.setBytesInBuffer(buffer.getBytesInBuffer());
		bufferReady = true;
		notify();
	}
	

	public boolean isTerminate() {
		return terminate;
	}

	public synchronized void setTerminate(boolean terminate) {
		this.terminate = terminate;
		this.bufferReady = true;
		this.buffer.setBytesInBuffer(0);
		notifyAll();
	}
	
	
	
}
