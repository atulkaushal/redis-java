package com.redis.datatypes;

import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * The Class RedisSetDataType.
 * 
 * @author Atul
 */
public class RedisSetDataType {

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

		// Add values to set
		jedis.sadd("cars", new String[] { "Ford", "BMW", "Chevy", "Cadillac", "Audi" });
		printAllValues(jedis, "cars");

		System.out.println("Is BMW part of cars:: " + jedis.sismember("cars", "BMW"));
		System.out.println("Is Limo part of cars:: " + jedis.sismember("cars", "Limo"));

		System.out.println("Elements in set cars:: " + jedis.scard("cars"));

		System.out.println("Remove BMW from set.");
		jedis.srem("cars", "BMW");
		printAllValues(jedis, "cars");

		// Move elements to different Set.
		System.out.println("Move element to different set");
		jedis.smove("cars", "trucks", "Ford");
		printAllValues(jedis, "cars");
		printAllValues(jedis, "trucks");

		jedis.sadd("otherCars", new String[] { "Jeep", "Audi" });
		printAllValues(jedis, "otherCars");

		Set<String> diff = jedis.sdiff(new String[] { "cars", "trucks", "otherCars" });
		System.out.print("SDiff cars truck otherCars result::\t");
		for (String value : diff) {
			System.out.print(value + "\t");
		}

		jedis.close();

	}

	/**
	 * Prints the all values.
	 *
	 * @param jedis the jedis
	 * @param setName the set name
	 */
	private static void printAllValues(Jedis jedis, String setName) {
		Set<String> values = jedis.smembers(setName);
		System.out.print("Elements of set " + setName + ":: ");
		for (String value : values) {
			System.out.print(value + "\t");
		}
		System.out.println();
	}

}
