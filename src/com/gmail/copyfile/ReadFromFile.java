package com.gmail.copyfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ReadFromFile implements Runnable {
	
	CommonBuffer commonBufer;
	File sourceFile;

	public ReadFromFile(CommonBuffer commonBufer, File sourceFile) {
		this.commonBufer = commonBufer;
		this.sourceFile = sourceFile;
	}


	@Override
	public void run() {
		// TODO Auto-generated method stub
		try (InputStream is = new FileInputStream(sourceFile)){
			
			Buffer tmpBuffer = new Buffer();
			byte[] buffer = new byte[1024];
			int readByte = 0;
			
			while ( ( readByte = is.read(buffer) ) > 0) {
				tmpBuffer.setBuffer(buffer);
				tmpBuffer.setBytesInBuffer(readByte);
				commonBufer.setBuffer(tmpBuffer);
			}
			
//			tmpBuffer.setBytesInBuffer(readByte);
//			commonBufer.setBuffer(tmpBuffer);
			
			commonBufer.setTerminate(true);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

}
