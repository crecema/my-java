package com.crecema.my.java.test.mock;

import java.util.Comparator;
import java.util.List;

public class StudentController {

    private final StudentService studentService = new StudentService();

    public List<Student> studentList() {
        return studentService.getStudentByName(null);
    }

    public Student student() {
        return studentService.getStudentById(1);
    }

    public Student minStudent(List<Student> studentList) {
        return studentList.stream()
                .filter(student -> student.getExtra().getSkills().contains("program"))
                .min(Comparator.comparing(Student::getAge))
                .orElse(null);
    }

}
