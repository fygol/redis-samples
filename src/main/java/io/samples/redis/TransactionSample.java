package io.samples.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

import java.util.List;

public class TransactionSample {
    public static void main(String[] args) {
        Jedis redis = new Jedis();

        Transaction tx = redis.multi();
        tx.set("key1", "val1");
        tx.incr("key1");
        Response<String> response = tx.get("key1");
        System.out.println("read response: " + response); // Response string
        // System.out.println("read response:" + response.get()); // redis.clients.jedis.exceptions.JedisDataException: Please close pipeline or multi block before calling this method.
        tx.set("key2", "val2");
        List<Object> exec = tx.exec();
        System.out.println(exec); // [OK, redis.clients.jedis.exceptions.JedisDataException: ERR value is not an integer or out of range, val1, OK]

        System.out.println("key1: " + redis.get("key1"));
        System.out.println("key2: " + redis.get("key2"));
        System.out.println("read response: " + response.get());
    }
}
