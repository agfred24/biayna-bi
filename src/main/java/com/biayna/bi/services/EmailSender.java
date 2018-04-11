package com.biayna.bi.services;

public interface EmailSender {

	public void setProcessedFileName(String processedFileName);
	public void setSubject(String subject);
	public void sendMail();
}
