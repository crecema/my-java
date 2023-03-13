package com.crecema.my.java.test.mock;

import com.alibaba.testable.core.tool.PrivateAccessor;
import org.junit.jupiter.api.Test;

import java.util.List;

public class StudentServiceTest {

    @Test
    public void testFilterStudent() {
        StudentService studentService =  new StudentService();
        List<Student> allStudentList = studentService.getAllStudent();
        List<Student> filterStudentList = PrivateAccessor.invoke(studentService, "filterStudent", allStudentList);
        System.out.println(filterStudentList);
    }

}
