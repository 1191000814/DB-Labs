package school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import school.entity.SC;
import school.mapper.SCMapper;
import school.utils.SchoolUtils;

import javax.annotation.Resource;

@SpringBootTest
public class SCTest{

    @Resource
    SCMapper mapper;

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
        SchoolUtils.myPrint(mapper.insert(new SC(1, 4, 90)));
    }

    @Test
    public void testDelete(){
        SchoolUtils.myPrint(mapper.deleteById(1, 4));
    }
}
