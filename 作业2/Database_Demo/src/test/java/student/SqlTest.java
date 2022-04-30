package student;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import student.entity.Student;
import student.mapper.StudentMapper;

import javax.annotation.Resource;

@SpringBootTest
public class SqlTest{

    @Resource
    StudentMapper mapper;

    @Test
    public void test1(){
        Student student = new Student(1, "xzq", 20, "男", 193710, "软件工程", "A01");
        System.out.println(student);
    }

    @Test
    public void test(){
        System.out.println(1);
    }
}
