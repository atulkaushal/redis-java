package com.redis.pubsub;

import redis.clients.jedis.Jedis;

/**
 * The Class Publisher.
 */
public class Publisher {

	/**
	 * Publish messages.
	 *
	 * @param jedis the jedis
	 * @param channelName the channel name
	 */
	public void publishMessages(Jedis jedis, String channelName) {
		
		jedis.publish(channelName, "Hello world first message on "+ channelName);
		jedis.publish(channelName, "Second message to "+channelName);
	}

}
