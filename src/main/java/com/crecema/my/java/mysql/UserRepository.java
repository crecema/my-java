package com.crecema.my.java.mysql;

import com.crecema.my.java.mysql.domain.User;

import java.util.List;

public class UserRepository {

    public User selectOneByEmail(String email) {
        String sql = "select * from user where email = ?";
        List<User> users = JdbcUtils.executeQuery(sql, UserMapper::mapRow, email);
        return users.isEmpty() ? null : users.get(0);
    }

}
