package school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import school.entity.Teacher;

@Repository
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher>{
}