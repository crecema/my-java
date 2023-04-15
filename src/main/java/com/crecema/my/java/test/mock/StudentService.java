package com.crecema.my.java.test.mock;

import java.util.List;
import java.util.Objects;

/**
 * 被测类
 */
public class StudentService {

    private final StudentRepository studentRepository = new StudentRepository();

    public List<Student> getStudentByName(String name) {
        // 调用下游普通方法
        List<Student> studentList = studentRepository.selectStudentList();
        if (name == null) {
            return studentList;
        } else if ("".equals(name)) {
            throw new IllegalArgumentException("name not be ''");
        } else {
            return studentList.stream()
                    .filter(student -> name.equals(student.getName()))
                    .toList();
        }
    }

    public Student getStudentById(Integer id) {
        List<Student> studentList = studentRepository.selectStudentList();
        // 调用自身private方法
        return filterStudentById(studentList, id);
    }

    private Student filterStudentById(List<Student> studentList, Integer id) {
        return studentList.stream()
                .filter(student -> Objects.equals(student.getId(), id))
                .findFirst()
                .orElse(null);
    }

    public StudentRepository getNewRepository() {
        return StudentRepository.newInstance();
    }

}
