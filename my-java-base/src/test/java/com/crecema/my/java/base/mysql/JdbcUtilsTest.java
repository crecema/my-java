package com.crecema.my.java.base.mysql;

import com.crecema.my.java.base.mysql.domain.User;
import org.junit.jupiter.api.Test;

public class JdbcUtilsTest {

    @Test
    public void testExecuteQuery() {
        String sql = "select * from user";
        User user = JdbcUtils.executeQuery(sql, UserMapper::mapRow).get(0);
        System.out.println(user);
    }

    // @Test
    public void insertData() {
        for (int i = 0; i < 1000000; i++) {
            String sql = "insert into user values(default, ?, ?, ?, ?, default, ?, ?, default, default)";
            String name = String.valueOf((int) (Math.random() * 1000000000));
            String email = name + "@gmail.com";
            String phone = "+86" + name;
            String idNumber = null;
            Integer sex = Math.random() > 0.5 ? 1 : 2;
            Integer age = (int) (Math.random() * 30) + 20;
            JdbcUtils.executeUpdate(sql, name, email, phone, idNumber, sex, age);
        }
    }

}
