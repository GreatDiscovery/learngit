package meite.example.redis;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author gavin
 * @date 2019/1/10 17:01
 */

@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate temple;

    public void setObject(String key, Object value) {
        if (StringUtils.isEmpty(key) || value == null) {
            return;
        }
        if (value instanceof String) {
            temple.opsForValue().set(key, (String) value);
        }
    }
}
