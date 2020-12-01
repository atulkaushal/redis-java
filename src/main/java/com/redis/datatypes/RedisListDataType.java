package com.redis.datatypes;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.ListPosition;

/**
 * The Class RedisListDataType.
 * 
 * @author Atul
 */
public class RedisListDataType {

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

		jedis.lpush("cars", "Ford");
		jedis.lpush("cars", "BMW");
		jedis.lpush("cars", "Chevy");
		jedis.rpush("cars", "Cadillac");
		jedis.rpush("cars", "Audi");

		printAll(jedis);

		System.out.println("Total elements in cars: " + jedis.llen("cars"));

		// remove item from tail.
		String item = jedis.rpop("cars");
		System.out.println("Removed item::" + item);
		System.out.println("After RPOP");
		printAll(jedis);

		// remove item from head.
		item = jedis.lpop("cars");
		System.out.println("Removed item::" + item);
		System.out.println("After LPOP");
		printAll(jedis);

		jedis.linsert("cars", ListPosition.AFTER, "Ford", "Lexus");
		System.out.println("After LINSERT");
		printAll(jedis);
		jedis.close();

	}

	/**
	 * Prints the all.
	 *
	 * @param jedis the jedis
	 */
	private static void printAll(Jedis jedis) {
		List<String> values;
		values = jedis.lrange("cars", 0, -1);
		for (String value : values) {
			System.out.print(value + "\t");
		}
		System.out.println();
	}
}
