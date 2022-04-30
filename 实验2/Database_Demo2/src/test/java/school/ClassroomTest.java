package school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import school.entity.Classroom;
import school.mapper.ClassroomMapper;
import school.utils.SchoolUtils;

import javax.annotation.Resource;

@SpringBootTest
public class ClassroomTest{

    @Resource
    ClassroomMapper mapper;

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
        SchoolUtils.myPrint(mapper.insert(new Classroom(11, 1, 678)));
    }

    @Test
    public void testDelete(){
        SchoolUtils.myPrint(mapper.deleteById(11));
    }
}
