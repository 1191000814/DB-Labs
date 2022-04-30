package school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import school.entity.Course;
import school.mapper.CourseMapper;
import school.utils.SchoolUtils;

import javax.annotation.Resource;

@SpringBootTest
public class CourseTest{

    @Resource
    private CourseMapper mapper;

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
        SchoolUtils.myPrint(mapper.insert(new Course(5, "计算机系统", 3, (float)3.5, 4)));
    }

    @Test
    public void testDelete(){
        SchoolUtils.myPrint(mapper.deleteById(1));
    }
}
