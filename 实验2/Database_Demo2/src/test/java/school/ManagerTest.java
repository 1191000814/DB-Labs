package school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import school.entity.Manager;
import school.mapper.ManagerMapper;
import school.utils.SchoolUtils;

import javax.annotation.Resource;

@SpringBootTest
public class ManagerTest{

    @Resource
    ManagerMapper mapper;

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
        SchoolUtils.myPrint(mapper.insert(new Manager(10, "sb", 20)));
    }

    @Test
    public void testDelete(){
        SchoolUtils.myPrint(mapper.deleteById(10));
    }
}
