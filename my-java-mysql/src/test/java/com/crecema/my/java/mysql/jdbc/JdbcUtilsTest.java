package com.crecema.my.java.mysql.jdbc;

import com.crecema.my.java.mysql.domain.Sex;
import com.crecema.my.java.mysql.domain.User;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;

public class JdbcUtilsTest {

    private static class UserMapper {
        public static User mapRow(ResultSet rs) {
            try {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setIdNumber(rs.getString("id_number"));
                user.setStatus(rs.getInt("status"));
                user.setSex(Sex.valueOf(rs.getInt("sex")));
                user.setAge(rs.getInt("age"));
                user.setCreateTime(rs.getTimestamp("create_time"));
                user.setUpdateTime(rs.getTimestamp("update_time"));
                return user;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

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
            int effect = JdbcUtils.executeUpdate(sql, name, email, phone, idNumber, sex, age);
        }
    }

}
