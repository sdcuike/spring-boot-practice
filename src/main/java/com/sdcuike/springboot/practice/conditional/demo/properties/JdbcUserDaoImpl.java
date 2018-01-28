package com.sdcuike.springboot.practice.conditional.demo.properties;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

/**
 * @author sdcuike
 * @date 2018/1/28
 * @since 2018/1/28
 */
@Repository
@Conditional(MySqlDriverPresentsCondition.class)
public class JdbcUserDaoImpl implements UserDao {
    @Override
    public List<String> getAllUserNames() {
        return Arrays.asList("jdbc", "test");
    }
}
