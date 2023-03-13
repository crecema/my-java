package com.crecema.my.java.test.mock;

import com.alibaba.testable.core.annotation.MockInvoke;

import java.util.List;

public class StudentServiceMock {

    @MockInvoke(targetClass = StudentRepository.class, targetMethod = "selectStudentList")
    public List<Student> selectStudentList() {
        return List.of(
                new Student(1, "mahaoran", 22, 1, null),
                new Student(2, "haoranma", 23, 1, null),
                new Student(3, "ranhaoma", 24, 1, null),
                new Student(4, "maranhao", 22, 1, null)
        );
    }

}
