package com.crecema.my.java.mysql.jdbc;

import com.crecema.my.java.mysql.domain.User;
import org.junit.jupiter.api.Test;

public class UserRepositoryTest {

    @Test
    void testSelectOneById() {
        UserRepository userRepository = new UserRepository();
        User user = userRepository.selectOne(1);
        System.out.println(user);
    }

}
