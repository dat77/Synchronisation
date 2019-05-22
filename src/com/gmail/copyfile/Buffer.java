package com.gmail.copyfile;

public class Buffer {
	
	public static final int BUFFER_SIZE = 1024; // buffer size in bytes 
	
	private byte[] buffer = new byte[BUFFER_SIZE];
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
