package com.crecema.my.java.test.mock;

import com.alibaba.testable.core.annotation.MockInvoke;
import com.alibaba.testable.core.annotation.MockNew;

import java.util.List;
import java.util.Objects;

/**
 * mock容器类
 */
public class StudentServiceMock {

    @MockNew
    public StudentRepository StudentRepository() {
        System.out.println("mock constructor method StudentRepository()");
        return new StudentRepository();
    }

    @MockInvoke(targetClass = StudentRepository.class, targetMethod = "selectStudentList")
    public List<Student> selectStudentList() {
        System.out.println("mock public method selectStudentList()");
        return List.of(
                new Student(1, "mahaoran", 22, 1, null),
                new Student(2, "haoranma", 23, 1, null),
                new Student(3, "ranhaoma", 24, 1, null),
                new Student(4, "maranhao", 22, 1, null),
                new Student(5, "mahaoran", 26, 1, null)
        );
    }

    @MockInvoke(targetClass = StudentRepository.class, targetMethod = "newInstance")
    public StudentRepository newInstance() {
        System.out.println("mock static method newInstance()");
        return new StudentRepository();
    }

    @MockInvoke(targetClass = StudentService.class, targetMethod = "filterStudentById")
    private Student filterStudentById(List<Student> studentList, Integer id) {
        System.out.println("mock public method filterStudentById()");
        return studentList.stream()
                .filter(student -> Objects.equals(student.getId(), id))
                .findFirst()
                .orElse(null);
    }

}
