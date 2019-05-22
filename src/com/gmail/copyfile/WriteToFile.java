package com.gmail.copyfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteToFile implements Runnable {

	CommonBuffer commonBufer;
	File destFile;

	public WriteToFile(CommonBuffer commonBufer, File destFile) {
		this.commonBufer = commonBufer;
		this.destFile = destFile;
	}

	@Override
	public void run() {
		try (OutputStream os = new FileOutputStream(destFile)) {
			Buffer tmpBuffer = new Buffer();

			while (!commonBufer.isTerminate()) {
				tmpBuffer = commonBufer.getBuffer();
				os.write(tmpBuffer.getBuffer(), 0, tmpBuffer.getBytesInBuffer());
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName()+" is terminated");	}

}
