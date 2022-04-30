package school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import school.entity.Classroom;

@Repository
@Mapper
public interface ClassroomMapper extends BaseMapper<Classroom>{
}
