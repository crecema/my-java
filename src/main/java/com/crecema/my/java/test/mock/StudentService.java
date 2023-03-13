package com.crecema.my.java.test.mock;

import java.util.List;
import java.util.stream.Collectors;

public class StudentService {

    private StudentRepository studentRepository = new StudentRepository();

    public List<Student> getAllStudent() {
        return studentRepository.selectStudentList();
    }

    public Student getStudent(Integer id) {
        return studentRepository.selectStudent(id);
    }

    private List<Student> filterStudent(List<Student> studentList) {
        return studentList.stream()
                .filter(student -> student.getAge() == 22)
                .collect(Collectors.toList());
    }

}
