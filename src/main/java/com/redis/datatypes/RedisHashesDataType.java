package com.redis.datatypes;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;

/**
 * The Class RedisHashesDataType.
 * 
 * @author Atul
 */
public class RedisHashesDataType {

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

		//Add a value to Hash.
		jedis.hset("users:Mike","name", "Mike");

		Map<String,String> johnMap= new HashMap<String,String>();
		johnMap.put("name", "John");
		johnMap.put("email", "John@john.com");
		johnMap.put("age", "25");

		jedis.hset("users:John",johnMap);

		//Get all Keys
		System.out.println("Print all keys for user:john");
		Set<String> keys=jedis.hkeys("users:John");
		for (String key : keys) {
			System.out.println(key);
		}

		//Get all values.
		System.out.println("Print all values for user:john");
		Map<String,String> map=jedis.hgetAll("users:John");
		for (String key : map.keySet()) {
			System.out.println(key+":"+map.get(key));
		}

		jedis.hincrBy("users:John", "age", 10);

		//Get all values.
		System.out.println("Print all values for user:john after incrementing age by 10");
		map=jedis.hgetAll("users:John");
		for (String key : map.keySet()) {
			System.out.println(key+":"+map.get(key));
		}

		System.out.println("Total key-value pairs in users:John :: "+ jedis.hlen("users:John"));

		jedis.hdel("users:John", "age");
		//Get all values.
		System.out.println("Print all values for user:john after deleting age attribute");
		map=jedis.hgetAll("users:John");
		for (String key : map.keySet()) {
			System.out.println(key+":"+map.get(key));
		}

		jedis.close();

	}
}
