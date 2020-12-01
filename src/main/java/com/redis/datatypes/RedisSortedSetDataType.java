package com.redis.datatypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

/**
 * The Class RedisSortedSetDataType.
 * 
 * @author Atul
 */
public class RedisSortedSetDataType {

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

		jedis.flushAll();

		Map<String, Double> userDOBMap = new HashMap<String, Double>();
		userDOBMap.put("Mike", 1981.0);
		userDOBMap.put("John", 1975.0);
		userDOBMap.put("Doe", 1990.0);

		// Add values to set
		jedis.zadd("users", userDOBMap);
		printAll(jedis);

		System.out.println("Rank of Mike ::" + jedis.zrank("users", "Mike"));

		System.out.println("Increment John's Score by 20");
		jedis.zincrby("users", 20, "John");
		printAll(jedis);
		jedis.close();
	}

	/**
	 * Prints the all.
	 *
	 * @param jedis the jedis
	 */
	private static void printAll(Jedis jedis) {
		Set<Tuple> values;
		values = jedis.zrangeWithScores("users", 0, -1);
		for (Tuple value : values) {
			System.out.print(value.getElement() + ":" + value.getScore() + "\t");
		}
		System.out.println();
	}

}
