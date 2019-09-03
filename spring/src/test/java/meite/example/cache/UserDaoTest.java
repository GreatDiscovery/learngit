package meite.example.cache;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author gavin
 * @date 2019/1/8 19:51
 */
@ContextConfiguration(locations = {"classpath*:applicationContext.xml"})
@RunWith(SpringRunner.class)
public class UserDaoTest {
    @Autowired
    UserDao userDao;

    @Test
    public void findByName() throws Exception {
        System.out.println(userDao.findByName("牛少东"));
    }

}