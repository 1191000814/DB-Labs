package school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import school.entity.Student;
import school.front.FrontStudent;

import java.util.List;

@Repository  //表示为mapper层的组件,并将其注入spring中,可以在其他文件中引用
@Mapper // 表示他要映射到mapper.xml文件中, 对应studentMapper.xml
public interface StudentMapper extends BaseMapper<Student>{

    List<FrontStudent> selectAll2(int group);
}