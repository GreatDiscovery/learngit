package meite.example.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author gavin
 * @date 2019/1/10 19:23
 */
@RunWith(SpringRunner.class)
public class RedisServiceTest {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void testSetObject() {
        stringRedisTemplate.opsForValue().set("sbw", "hello");
    }
}