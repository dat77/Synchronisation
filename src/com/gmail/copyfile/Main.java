package com.gmail.copyfile;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CommonBuffer commonBuffer = new CommonBuffer();
		
		ReadFromFile readFromFile = new ReadFromFile(commonBuffer, new File("DOCs\\Prasentation.pptx"));
		WriteToFile writeToFile = new WriteToFile(commonBuffer, new File("DOCs\\Prasentation_copy.pptx"));
		
		Thread threadFrom = new Thread(readFromFile);
		Thread threadTo = new Thread(writeToFile);
		
		threadFrom.start();
		threadTo.start();

	}

}
