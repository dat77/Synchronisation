package com.gmail.copyfile;

import java.io.File;

public class Main {

	public static void main(String[] args) {

		CommonBuffer commonBuffer = new CommonBuffer();
		
		ReadFromFile readFromFile = new ReadFromFile(commonBuffer, new File("DOCs\\IvanIliin.docx"));
		WriteToFile writeToFile = new WriteToFile(commonBuffer, new File("DOCs\\IvanIliin_copy.docx"));
		
		Thread threadFrom = new Thread(readFromFile, "Read thread");
		Thread threadTo = new Thread(writeToFile, "Write thread");
		
		threadFrom.start();
		threadTo.start();

	}

}
