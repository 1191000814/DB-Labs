package school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import school.entity.Course;

@Repository
@Mapper
public interface CourseMapper extends BaseMapper<Course>{
}
