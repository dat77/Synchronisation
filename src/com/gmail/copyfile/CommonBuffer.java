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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		byte[] tmpBytes = new byte[1024];
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.buffer = buffer;
		bufferReady = true;
		notify();
	}
	
	public boolean isBufferReady() {
		return bufferReady;
	}
	
	public void setBufferReady(boolean bufferReady) {
		this.bufferReady = bufferReady;
	}

	public boolean isTerminate() {
		return terminate;
	}

	public void setTerminate(boolean terminate) {
		this.terminate = terminate;
	}
	
	
	
}
