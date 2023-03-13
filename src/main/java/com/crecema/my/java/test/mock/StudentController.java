package com.crecema.my.java.test.mock;

import java.util.Comparator;
import java.util.List;

public class StudentController {

    private StudentService studentService = new StudentService();

    public List<Student> studentList() {
        return studentService.getAllStudent();
    }

    public Student student() {
        return studentService.getStudent(1);
    }

    public Student minStudent(List<Student> studentList) {
        return studentList.stream()
                .filter(student -> student.getExtra().getSkills().contains("program"))
                .min(Comparator.comparing(Student::getAge))
                .orElse(null);
    }

}
