package school;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import school.entity.Dept;
import school.mapper.DeptMapper;
import school.utils.SchoolUtils;

import javax.annotation.Resource;

@SpringBootTest
public class DeptTest{

    @Resource
    DeptMapper mapper;

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
        SchoolUtils.myPrint(mapper.insert(new Dept(10, "天坑", 1)));
    }

    @Test
    public void testDelete(){
        SchoolUtils.myPrint(mapper.deleteById(10));
    }
}
