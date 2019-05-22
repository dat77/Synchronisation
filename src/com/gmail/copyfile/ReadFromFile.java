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
		try (InputStream is = new FileInputStream(sourceFile)){
			
			Buffer tmpBuffer = new Buffer();
			byte[] buffer = new byte[Buffer.BUFFER_SIZE];
			int readByte = 0;
			
			long stepsCount = sourceFile.length()/Buffer.BUFFER_SIZE;
			long currentStep = 0;
			long progressStep =stepsCount/10; 
						
			while ( ( readByte = is.read(buffer) ) > 0) {
				tmpBuffer.setBuffer(buffer);
				tmpBuffer.setBytesInBuffer(readByte);
				commonBufer.setBuffer(tmpBuffer);
				currentStep++;
				if (currentStep == progressStep) {
					Thread.sleep(200);
					progressStep += stepsCount/10;
					System.out.println(currentStep*Buffer.BUFFER_SIZE + " of "+sourceFile.length());
				}
				
			}
			
			System.out.println(((currentStep-1)*Buffer.BUFFER_SIZE+ tmpBuffer.getBytesInBuffer()) + " of "+sourceFile.length());			
			commonBufer.setTerminate(true);
						
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(Thread.currentThread().getName()+" is terminated");
	}

}
