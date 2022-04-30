package school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import school.entity.Building;
import school.mapper.BuildingMapper;
import school.utils.SchoolUtils;

import javax.annotation.Resource;

@SpringBootTest
public class BuildingTest{

    @Resource
    BuildingMapper mapper;

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
        SchoolUtils.myPrint(mapper.insert(new Building(5, "A01公寓", 1)));
    }

    @Test
    public void testDelete(){
        SchoolUtils.myPrint(mapper.deleteById(5));
    }
}
