package com.crecema.my.java.mysql;

import com.crecema.my.java.mysql.domain.User;
import org.junit.jupiter.api.Test;

public class JdbcUtilsTest {

    @Test
    public void testExecuteQuery() {
        String sql = "select * from user";
        User user = JdbcUtils.executeQuery(sql, UserMapper::mapRow).get(0);
        System.out.println(user);
    }

}
