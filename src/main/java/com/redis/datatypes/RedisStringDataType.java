package com.redis.datatypes;

import redis.clients.jedis.Jedis;

/**
 * The Class RedisStringDataType.
 * 
 * @author Atul
 */
public class RedisStringDataType {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// Connecting to Redis server on localhost
		Jedis jedis = new Jedis("127.0.0.1");
		System.out.println("Connection to server sucessfully");
		// check whether server is running or not
		System.out.println("Server is running: " + jedis.ping());

		// set the data in redis string
		jedis.set("tutorial-name", "Redis tutorial");

		// Set if key does not exists.
		jedis.setnx("tutorial-name1", "Redis tutorial1");

		// Get the stored data and print it
		System.out.println("Stored string in redis:: " + jedis.get("tutorial-name"));
		System.out.println("Stored string in redis:: " + jedis.get("tutorial-name1"));

		// check if key exists or not.
		System.out.println("Key exists :: " + jedis.exists("tutorial-name"));

		jedis.close();
	}
}