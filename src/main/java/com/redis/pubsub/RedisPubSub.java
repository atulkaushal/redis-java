package com.redis.pubsub;

import redis.clients.jedis.Jedis;

/**
 * The Class RedisPubSub.
 */
public class RedisPubSub {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Jedis jedis = new Jedis("127.0.0.1");
		String channelName="redis-chat";
		jedis.publish(channelName, "Hello");
		jedis.publish(channelName, "how are you");
		jedis.publish(channelName, "Good Morning");
		new Publisher().publishMessages(jedis, "redis-chat");
		jedis.close();
	}

}
