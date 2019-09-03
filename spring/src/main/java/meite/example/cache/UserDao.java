package meite.example.cache;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 并没有写完
 *
 * @author gavin
 * @date 2019/1/8 19:44
 */
@Repository
public class UserDao {
    private JdbcTemplate template;

    @Resource(name = "dataSource")
    public void setDataSource(DataSource dataSource) {
        System.out.println(dataSource.toString());
        Assert.notNull(dataSource, "dataSource不能为null");
        template = new JdbcTemplate(dataSource);
    }

    public int findByName(String name) {
        String sql = "select buyfee from stu where stuname = ?";
        return template.queryForObject(sql, new Object[]{name}, Integer.class);
    }
}
