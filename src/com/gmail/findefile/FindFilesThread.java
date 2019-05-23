package com.gmail.findefile;

import java.nio.file.Path;
import java.util.concurrent.Callable;

public class FindFilesThread implements Callable<Path[]> {
	
	private Path targetFolder;
	private Path fileToFind;
	
	public FindFilesThread(Path targetFolder, Path fileToFind) {
		this.targetFolder = targetFolder;
		this.fileToFind = fileToFind;
	}

	@Override
	public Path[] call() throws Exception {
		return FileHandler.getFilesFromFolder(targetFolder, fileToFind);
	}

}
