package com.crecema.my.java.test.mock;

import java.util.Collections;
import java.util.List;

public class StudentRepository {

    public List<Student> selectStudentList() {
        // 查数据库
        return Collections.emptyList();
    }

    public Student selectStudent(Integer id) {
        // 查数据库
        return null;
    }

}
