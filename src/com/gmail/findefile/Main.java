package com.gmail.findefile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {
		Path targetFolder = null;
		Path fileToFind = null;
		
		try {
			JFileChooser sfc = new JFileChooser();
			sfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			sfc.showDialog(null, "Choose target folder");			
			targetFolder = sfc.getSelectedFile().toPath();
			fileToFind = Paths.get(JOptionPane.showInputDialog("file to find"));
			
			ExecutorCompletionService<Path[]> completionService = new ExecutorCompletionService<Path[]>(Executors.newFixedThreadPool(4));
			
			ArrayList<Future<Path[]>> futures = new ArrayList<>();
			final Path fileToFind1 = fileToFind;
			Files.list(targetFolder).forEach((x) -> {
				if (Files.isDirectory(x)) {
					futures.add(completionService.submit(new FindFilesThread(x, fileToFind1)));
				} else {
					if (x.endsWith(fileToFind1)) {
						System.out.println(x.toString());
					}
				}
			});
			
			for (Future<Path[]> future : futures) {
				try {
					for (Path path : future.get()) {
						if (path != null && path.toString() != "") {
							System.out.println(path.toString());
						}
					}
				} catch (InterruptedException | ExecutionException e) {
					e.printStackTrace();
				}
			}
			
			
		}catch (NullPointerException e) {
			System.out.println("file or folder has not been choosed");
		}catch (IOException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		
	}

}
