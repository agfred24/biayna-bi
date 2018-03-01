package com.biayna.bi.MQ;

import java.io.IOException;
import java.io.Serializable;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.SerializationUtils;

/**
 * The MLS History Data Producer produces messages to the history data consumer queue.
 * This class extends EndPoint abstract class
 * 
 * @author Fred A.
 *
 */
public class Publisher extends EndPoint{
	
	public Publisher(String endPointName) throws IOException, TimeoutException{
		super(endPointName);
	}

	public void sendMessage(Serializable object) throws IOException {
	    channel.basicPublish("",endPointName, null, SerializationUtils.serialize(object));
	}	
}