package com.biayna.bi.MQ;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * The EndPoint is an abstract base class to establish a new connection, 
 * create a channel and declare a queue.
 * 
 * @author Fred A.
 *
 */
public abstract class EndPoint {
	protected Channel channel;
	protected Connection connection;
	protected String endPointName;
	
	private static Logger logger = LogManager.getLogger();
	
	public EndPoint(String endpointName) throws IOException, TimeoutException {
		this.endPointName = endpointName;

		// Create a connection factory
		ConnectionFactory factory = new ConnectionFactory();

		// hostname of your rabbitmq server
		factory.setHost("localhost");

		factory.setUsername("guest");
		factory.setPassword("guest");
		// factory.setPort(15672);

		// getting a connection
		connection = factory.newConnection();

		// creating a channel
		channel = createChannel();

		/**
		 * The queueDeclare method call does nothing if the queue already exists,
		 * otherwise it creates the queue itself.
		 * ARGUMENTS:
		 * durable - RabbitMQ will never lose the queue if a crash occurs
		 * exclusive - if queue only will be used by one connection
		 * autodelete - queue is deleted when last consumer unsubscribes
		 */
		boolean durable = true; 
		boolean exclusive = false; 
		boolean autoDelete = false;
		channel.queueDeclare(endPointName, durable, exclusive, autoDelete, null);		
	}
	
	public Channel createChannel() {
		try {
			return connection == null ? null : connection.createChannel();
		} catch (final Exception ex) {
			logger.error("Failed to create channel" + ex);
			return null;
		}
	}

	/**
	 * Close connection. Not necessary as it happens implicitly any way.
	 * 
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public void closeConnection() {
		if (connection == null)	{
			return;
		}
		try	{
			connection.close();
		} catch (final Exception ex) {
			logger.error("Failed to close connection: " + ex);
		} finally {
			connection = null;
		}
	}
	
	/**
	 * Close channel. Not necessary as it happens implicitly any way.
	 * 
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public void closeChannel() {
		// isOpen is not fully trustable!
		if ((channel == null) || (!channel.isOpen())) {
			return;
		}
		try {
			channel.close();
		} catch (final Exception ex) {
			logger.error("Failed to close channel: " + ex);
		} finally {
			channel = null;
		}
	}
	
			
}
