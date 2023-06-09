package com.crecema.my.java.mysql.mybatis;

import com.crecema.my.java.mysql.domain.User;
import com.crecema.my.java.mysql.mybatis.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

public class UserMapperTest {

    private static SqlSessionFactory sqlSessionFactory = null;

    @BeforeAll
    static void init() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testSelectOneById() {
        try (var sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectOneById(10000000);
            System.out.println(user);
        }
    }

}
