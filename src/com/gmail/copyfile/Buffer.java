package com.gmail.copyfile;

public class Buffer {
	
	private byte[] buffer = new byte[1024];
	private int bytesInBuffer;
	
	public Buffer() {
	}

	public byte[] getBuffer() {
		return buffer;
	}

	public void setBuffer(byte[] buffer) {
		this.buffer = buffer;
	}

	public int getBytesInBuffer() {
		return bytesInBuffer;
	}

	public void setBytesInBuffer(int bytesInBuffer) {
		this.bytesInBuffer = bytesInBuffer;
	}
	
	

}
