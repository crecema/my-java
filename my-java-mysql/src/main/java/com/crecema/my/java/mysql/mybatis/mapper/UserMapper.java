package com.crecema.my.java.mysql.mybatis.mapper;

import com.crecema.my.java.mysql.domain.User;

public interface UserMapper {

    User selectOneById(int id);

}
