package com.biayna.bi.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import com.biayna.bi.MQ.MLSHistoryDataFileInfo;

public class HistoricDataProcessor {
	private MLSHistoryDataFileInfo fileInfo;
	
	public HistoricDataProcessor(MLSHistoryDataFileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}
	public boolean processHistoricData() throws IOException {
		Path path = Paths.get(fileInfo.getPath());
				
		try (Stream<String> stream = Files.lines(path)) {	 
			stream.forEachOrdered(s -> {
				
				System.out.println(s);
				
			});
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		
		return true;
	}
}
