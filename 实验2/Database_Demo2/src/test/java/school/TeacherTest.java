package school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import school.entity.Teacher;
import school.mapper.TeacherMapper;
import school.utils.SchoolUtils;

import javax.annotation.Resource;

@SpringBootTest
public class TeacherTest{

    @Resource
    TeacherMapper mapper;

    @Test
    public void testSelect(){
        SchoolUtils.myPrint(mapper.selectById(1));
    }

    @Test
    public void testSelectAll(){
        SchoolUtils.myPrint(mapper.selectAll());
    }

    @Test
    public void testInsert(){
        SchoolUtils.myPrint(mapper.insert(new Teacher(5, "fgx", 4, 60000)));
    }

    @Test
    public void testDelete(){
        SchoolUtils.myPrint(mapper.deleteById(5));
    }
}
