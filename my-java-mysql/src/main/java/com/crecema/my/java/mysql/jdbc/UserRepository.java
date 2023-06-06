package com.crecema.my.java.mysql.jdbc;

import com.crecema.my.java.mysql.domain.Sex;
import com.crecema.my.java.mysql.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepository {

    private static class UserMapper {
        public static User map(ResultSet rs) throws SQLException {
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
        }
    }

    public User selectOneByEmail(String email) {
        String sql = "select * from user where email = ?";
        List<User> users = JdbcUtils.executeQuery(sql, UserMapper::map, email);
        return users.isEmpty() ? null : users.get(0);
    }

}
