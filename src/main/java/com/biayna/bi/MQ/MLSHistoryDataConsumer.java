package com.biayna.bi.MQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang.SerializationUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;
import com.biayna.bi.common.exceptions.NetworkException;
import com.biayna.bi.services.HistoricDataProcessor;

/**
 * The MLS History Data Consumer consumes messages off of the history data consumer queue.
 * This class extends EndPoint abstract class and implements com.rabbitmq.client.Consumer
 * 
 * @author Fred A.
 *
 */
public class MLSHistoryDataConsumer extends EndPoint implements Consumer {

	private static MLSHistoryDataConsumer instance = null;
	private static String endPointName = "historyDataConsumerQueue"; // this is the name of our queue
	
	private static Logger logger = LogManager.getLogger();
	
	/**
	 * The constructor is private since this class is Singleton.
	 * With the Singleton design pattern you can:
	 *  	Ensure that only one instance of a class is created
	 * 		Provide a global point of access to the object
	 * @throws IOException
	 * @throws TimeoutException
	 * @throws NetworkException 
	 */	
	private MLSHistoryDataConsumer() throws IOException, TimeoutException, NetworkException {
		super(endPointName);
	}
	
	/**
	 * this method creates an instance of this class and
	 * starts the consumer to start receiving messages.
	 * @throws IOException
	 * @throws TimeoutException
	 * @throws NetworkException 
	 */
	public static void getInstance() throws IOException, TimeoutException, NetworkException {
		if (instance == null) {
			instance = new MLSHistoryDataConsumer();
			instance.receiveMessage();
		}
	}

	/**
	 * channel starts the basic consumer by setting the 
	 * auto acknowledgment to false. 
	 * we need to acknowledge manually.
	 * @throws IOException
	 */
	public void receiveMessage() throws IOException {
		channel.basicConsume(endPointName, false, this);
	}

	/**
	 * Called when consumer is registered.
	 */
	public void handleConsumeOk(String consumerTag) {
		logger.info("Consumer " + consumerTag + " registered");
	}

	/**
	 * Called when new message is available.
	 */
	public void handleDelivery(String consumerTag, Envelope env, BasicProperties props, byte[] body)
			throws IOException {
		
		long deliveryTag = env.getDeliveryTag();
		if (processMessage(body)) {
			channel.basicAck(deliveryTag, true);
		} else {
			channel.basicNack(deliveryTag, false, true);
			logger.info("Consumer did not acknowledge the message: " + consumerTag );
		}
	}
	
	private boolean processMessage(byte[] body) throws IOException{
		MLSHistoryDataFileInfo fileInfo = (MLSHistoryDataFileInfo) SerializationUtils.deserialize(body);
		logger.info("Consumer Processing File Path: " + fileInfo.getPath());
		logger.info("Consumer Processing File Name: " + fileInfo.getName());
		HistoricDataProcessor dataProcessor = new HistoricDataProcessor(fileInfo);
		if (dataProcessor.processHistoricData()) {
			return true;
		}
		logger.info("Consumer cannot process the file, Processing File Path: Requeue " + fileInfo.getPath());
		return false;
	}

	public void handleCancel(String consumerTag) {
	}

	public void handleCancelOk(String consumerTag) {
	}

	public void handleRecoverOk(String consumerTag) {
	}

	public void handleShutdownSignal(String consumerTag, ShutdownSignalException arg1) {
	}

}