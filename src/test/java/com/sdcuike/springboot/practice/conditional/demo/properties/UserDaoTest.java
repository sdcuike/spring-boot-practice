package com.sdcuike.springboot.practice.conditional.demo.properties;

import com.sdcuike.springboot.practice.SpringApplicationBoot;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author sdcuike
 * @date 2018/1/28
 * @since 2018/1/28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SpringApplicationBoot.class)
public class UserDaoTest {
    @Autowired
    private UserDao userDao;
    
    
    @Test
    public void testMysqlDbType() {
        final List<String> allUserNames = userDao.getAllUserNames();
         assertEquals(Arrays.asList("jdbc", "test"), allUserNames);
    }
}
