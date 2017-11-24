package io.samples.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import static org.assertj.core.api.Assertions.assertThat;

public class StringTest {
    Jedis redis;

    @Before
    public void setUp() throws Exception {
        redis = new Jedis("localhost", 6379);
    }

    @After
    public void tearDown() throws Exception {
        redis.quit();
    }

    @Test
    public void set_and_get() throws Exception {
        redis.set("key1", "value1");
        assertThat(redis.get("key1")).isEqualTo("value1");
    }
}
