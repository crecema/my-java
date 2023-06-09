package com.crecema.my.java.mysql.jdbc;

import com.crecema.my.java.base.util.JsonUtils;
import com.crecema.my.java.mysql.domain.Sex;
import com.crecema.my.java.mysql.domain.User;

import java.util.List;

public class UserRepository {

    private final JdbcUtils.Mapper<User> userMapper = (rs) -> {
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
        user.setExtraInfo(JsonUtils.toObject(rs.getString("extra_info"), User.ExtraInfo.class));
        return user;
    };

    public User selectOneById(Integer id) {
        String sql = "select * from user where id = ?";
        List<User> users = JdbcUtils.executeQuery(sql, userMapper, id);
        return users.isEmpty() ? null : users.get(0);
    }

    public User selectOneByEmail(String email) {
        String sql = "select * from user where email = ?";
        List<User> users = JdbcUtils.executeQuery(sql, userMapper, email);
        return users.isEmpty() ? null : users.get(0);
    }

}
