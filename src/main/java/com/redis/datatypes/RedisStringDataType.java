package com.redis.datatypes;

import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.params.SetParams;

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
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// Connecting to Redis server on localhost
		Jedis jedis = new Jedis("127.0.0.1");
		System.out.println("Connection to server sucessfully");
		// check whether server is running or not
		System.out.println("Server is running: " + jedis.ping());
		
		jedis.flushAll();

		// set the data in redis string
		jedis.set("tutorial-name", "Redis tutorial");

		// Set if key does not exists.
		jedis.setnx("tutorial-name1", "Redis tutorial1");

		// Get the stored data and print it
		System.out.println("Stored string in redis:: " + jedis.get("tutorial-name"));
		System.out.println("Stored string in redis:: " + jedis.get("tutorial-name1"));

		// check if key exists or not.
		System.out.println("Key exists :: " + jedis.exists("tutorial-name"));
		
		jedis.set("Foo", "100");
		
		System.out.println("Value of key Foo:: " +jedis.get("Foo"));
		
		jedis.incrBy("Foo", 100l);
		System.out.println("Value of key Foo after increment by 100 :: " +jedis.get("Foo"));
		
		//Set multiple keys in one command.
		jedis.mset(new String[] {"database:name","Redis","database:port","6379"});
		System.out.println(jedis.get("database:name"));
		
		//this will set all three value if none of the key already exists.
		jedis.msetnx(new String[] {"database:name","Redis","database:port","6379","database:host","localhost"});
		
		//rename existing key
		jedis.rename("database:port", "databasePort");
		
		jedis.set("Greeting", "Hello world from Redis", new SetParams().ex(5));
		
		//get all keys.
		printAllKeys(jedis);
		System.out.println("Sleeping for 10 seconds. Key Greeting should be removed as expiration time is 5 seconds.");
		new Thread();
		Thread.sleep(10000);
		
		printAllKeys(jedis);
	

		jedis.close();
	}

	/**
	 * Prints the all keys.
	 *
	 * @param jedis the jedis
	 */
	private static void printAllKeys(Jedis jedis) {
		System.out.println("Get all keys and print values.");
		Set<String> allKeys=jedis.keys("*");
		for (String key : allKeys) {
			System.out.println(key+":"+jedis.get(key));
		}
	}
}