package com.crecema.my.java.base.mysql;

import com.crecema.my.java.base.mysql.domain.Sex;
import com.crecema.my.java.base.mysql.domain.User;

import java.sql.ResultSet;

public class UserMapper {

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
