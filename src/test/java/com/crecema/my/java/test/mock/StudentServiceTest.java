package com.crecema.my.java.test.mock;

import com.alibaba.testable.core.tool.PrivateAccessor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * 测试类
 */
public class StudentServiceTest {

    /**
     * 测试public方法，mock下游非构造方法/构造方法
     */
    @Test
    public void testGetStudentByName() {
        StudentService studentService =  new StudentService();
        // 异常case
        Assertions.assertThrows(IllegalArgumentException.class, () -> studentService.getStudentByName(""));
        // 正常case
        List<Student> studentList = studentService.getStudentByName("mahaoran");
        Assertions.assertNotNull(studentList);
        Assertions.assertEquals(2, studentList.size());
    }

    /**
     * 测试public方法，mock下游static方法
     */
    @Test
    public void testGetNewRepository() {
        StudentService studentService =  new StudentService();
        StudentRepository newRepository = studentService.getNewRepository();
        Assertions.assertNotNull(newRepository);
    }

    /**
     * 测试public方法，mock自身private方法
     */
    @Test
    public void testGetStudentById() {
        StudentService studentService =  new StudentService();
        Student student = studentService.getStudentById(5);
        Assertions.assertNotNull(student);
    }

    /**
     * 测试private方法
     */
    @Test
    public void testFilterStudentById() {
        StudentService studentService =  new StudentService();
        List<Student> studentList = studentService.getStudentByName("mahaoran");
        Student filterStudent = PrivateAccessor.invoke(studentService, "filterStudentById", studentList, 5);
        Assertions.assertNotNull(filterStudent);
    }

}
