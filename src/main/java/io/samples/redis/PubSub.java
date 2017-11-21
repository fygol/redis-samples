package io.samples.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

public class PubSub {
    public static void main(String[] args) {
        new Thread(new Consumer()).start();
        new Thread(new Producer()).start();
    }
}

class Producer implements Runnable {
    public Producer() {
    }

    public void run() {
        Jedis redis = new Jedis("localhost", 6379);
        while (true) {
            System.out.println("Send message");
            redis.publish("messages", String.valueOf(System.currentTimeMillis()));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Consumer extends JedisPubSub implements Runnable {
    private Jedis redis;

    public Consumer() {
        redis = new Jedis("localhost", 6379);
    }

    @Override
    public void onMessage(String channel, String message) {
        System.out.println("Received message: " + message + "/" + channel);
    }

    public void run() {
        redis.subscribe(this, "messages");
    }
}
