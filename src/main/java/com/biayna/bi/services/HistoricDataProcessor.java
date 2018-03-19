package com.biayna.bi.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.stream.Stream;

import com.biayna.bi.MQ.MLSHistoryDataFileInfo;
import com.biayna.bi.domain.listing.Address;
import com.biayna.bi.domain.listing.Listing;
import com.biayna.bi.domain.listing.PropertyDetails;

public class HistoricDataProcessor {
	private MLSHistoryDataFileInfo fileInfo;
	
	public HistoricDataProcessor(MLSHistoryDataFileInfo fileInfo) {
		this.fileInfo = fileInfo;
	}	
	
	public boolean processHistoricData() {
		Path path = Paths.get(fileInfo.getPath());
		HashMap<Integer, String> headerNumberMap = new HashMap<>();
		HashSet<Listing> listings = new HashSet<>();
		try (Stream<String> stream = Files.lines(path)) {	 
			stream.forEachOrdered(s -> {
				/*int col = 0;
				if (stream.count()==0) {
					String[] headers = s.split(",");
					for (String header : headers) {
						headerNumberMap.put(col, header);
					}
				} else {					
					String[] values = s.split(",");
					Address address = new Address();
					PropertyDetails details = new PropertyDetails();
					Listing listing = new Listing(address, details, false, null);
					
					for (String value : values) {

						if (value.equals("MLS Area")) {
							
						}
						
						
						
						
					}
					listings.add(listing);
					//rows = stream.i
*/				System.out.println(s);
				
								
			});
		} catch (IOException ex) {
			ex.printStackTrace();
		}

		EmailService emailService = new EmailServiceImpl();
		//String emialMessage = emailService.writeEmailMessage("hello");
		String successMessage = "The uploaded file \"" + fileInfo.getName() + "\" has been processed and data has been stored successfully.";
		String successSubject = "File has been processed.";
		emailService.setProcessedFileName(successMessage);
		emailService.setSubject(successSubject);
		emailService.sendMail();
		return true;
	}
}
