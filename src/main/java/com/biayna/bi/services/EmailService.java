package com.biayna.bi.services;

public interface EmailService {

	public void setProcessedFileName(String processedFileName);
	public void setSubject(String subject);
	public void sendMail();
}
