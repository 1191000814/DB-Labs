package school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import school.entity.Student;
import school.mapper.StudentMapper;
import school.utils.SchoolUtils;

import javax.annotation.Resource;

@SpringBootTest
public class StudentTest{

    @Resource
    StudentMapper mapper;

    @Test
    public void testSelect(){
        SchoolUtils.myPrint(mapper.selectById(1));
    }

    @Test
    public void testSelectAll(){
        SchoolUtils.myPrint(mapper.selectAll2());
    }

    @Test
    public void testInsert(){
        SchoolUtils.myPrint(mapper.insert(new Student(7, "zabu", 21, 3)));
    }

    @Test
    public void testDelete(){
        SchoolUtils.myPrint(mapper.deleteById(4));
    }
}