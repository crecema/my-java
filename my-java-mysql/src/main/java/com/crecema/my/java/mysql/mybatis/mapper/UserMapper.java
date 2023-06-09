package com.crecema.my.java.mysql.mybatis.mapper;

import com.crecema.my.java.mysql.domain.User;

import java.util.List;

public interface UserMapper {

    User selectOne(int id);

    List<User> selectList(User condition);

    int insert(User user);

    int update(User user, User condition);

    int delete(User condition);

}
