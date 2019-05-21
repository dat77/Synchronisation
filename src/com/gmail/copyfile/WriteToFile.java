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
		// TODO Auto-generated method stub
		try (OutputStream os = new FileOutputStream(destFile)) {
			Buffer tmpBuffer = new Buffer();

			while (!commonBufer.isTerminate()) {
				tmpBuffer = commonBufer.getBuffer();
				os.write(tmpBuffer.getBuffer(), 0, tmpBuffer.getBytesInBuffer());
			}
			
//			if (commonBufer.getBuffer().getBytesInBuffer() > 0) {
//				tmpBuffer = commonBufer.getBuffer();
//				os.write(tmpBuffer.getBuffer(), 0, tmpBuffer.getBytesInBuffer());
//			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

}
