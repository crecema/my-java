package com.crecema.my.java.test.mock;

import com.alibaba.testable.core.tool.OmniConstructor;
import com.crecema.my.java.util.JsonUtils;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class StudentControllerTest {

    @Test
    public void test() {
        String stime = "1533816508000";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        System.out.println(new Date(Long.valueOf(stime)));
    }

    @Test
    public void testStudentList() {
        StudentController controller = new StudentController();
        List<Student> studentList = controller.studentList();
        System.out.println(JsonUtils.toJson(studentList));

        System.out.println(JsonUtils.toJson(controller.student()));

    }

    @Test
    public void testMinStudent() {
        Student student = OmniConstructor.newInstance(Student.class);
        student.getExtra().getSkills().add("program");
        System.out.println(JsonUtils.toJson(student));
    }

}
