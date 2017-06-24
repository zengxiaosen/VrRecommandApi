import com.sohu.rdcinf.vr.dao.IUserDao;
import com.sohu.rdcinf.vr.model.User;
import com.sohu.rdcinf.vr.utils.JedisPoolUtil;
import com.sohu.rdcinf.vr.utils.JedisUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by zengxiaosen on 2017/6/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring/app-root.xml")
public class RedisTest extends AbstractJUnit4SpringContextTests{
    @Autowired
    private IUserDao userDao;
    JedisPoolUtil pool = null;

    @Test
    public void testRedis(){
        Jedis jedis = JedisUtil.getInstance().getJedis();
        jedis.set("hello", "123");
        String msg = jedis.get("hello");
        System.out.println(msg);
        jedis.close();
    }

    /**
     * 获取
     */
    @Test
    public void testGetList(){
        String id = "recommend:cf:08|23|B2|7D|2C|68";
        List<String> result = userDao.getList(id);
        Assert.assertNull(result.size());
    }

    /**
     * 获取
     */
    @Test
    public void testGet() {
        String id = "hello";
        boolean result = userDao.get(id);
        Assert.assertTrue(result);
    }

    /**
     * 通过key删除单个
     */
    @Test
    public void testDelete(){
        String key = "hello";
        userDao.delete(key);
    }

    /**
     * 新增
     */
    @Test
    public void testAddUser() {
        User user = new User();
        user.setId("user1");
        user.setName("java2000_wl");
        boolean result = userDao.add(user);
        Assert.assertTrue(result);
    }

}
