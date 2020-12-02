package com.redis.pubsub;

import java.util.ArrayList;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * The Class Subscriber.
 */
public class Subscriber {

	public static List<String> messageList = new ArrayList<String>();

	/**
	 * Subscribe messages.
	 *
	 * @param jedis       the jedis
	 * @param channelName the channel name
	 * @return the jedis pub sub
	 */
	public JedisPubSub subscribeMessages() {
		JedisPubSub sub = new JedisPubSub() {

			/**
			 * On message.
			 *
			 * @param channel the channel
			 * @param message the message
			 */
			@Override
			public void onMessage(String channel, String message) {
				/*
				 * System.out.println("Channel " + channel + " has sent a message : " + message
				 * ); if(channel.equals("redis-chat")) { Unsubscribe from channel redis-chat
				 * after first message is received. //unsubscribe(channel); }
				 */
				messageList.add(message.toString());
				System.out.println("Message received: " + message.toString());
			}

			/**
			 * On subscribe.
			 *
			 * @param channel            the channel
			 * @param subscribedChannels the subscribed channels
			 */

			@Override 
			public void onPSubscribe(String channel, int subscribedChannels) {
				System.out.println("Client is Subscribed to channel : " + channel);
				System.out.println("Client is Subscribed to " + subscribedChannels +
						" no. of channels"); }


		};

		return sub;
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String... args)
	{
		Jedis jedis = new Jedis("127.0.0.1");
		System.out.println("Connection to server sucessfully");
		// check whether server is running or not
		System.out.println("Server is running: " + jedis.ping());
		String channelName="redis-chat";
		Subscriber subscriber =new Subscriber();
		jedis.subscribe(subscriber.subscribeMessages(), channelName);
		//jedis.psubscribe(subscriber.subscribeMessages(), "Redis-*");
		jedis.close();
	}
}
