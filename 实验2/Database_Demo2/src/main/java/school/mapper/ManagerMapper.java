package school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import school.entity.Manager;

@Repository
@Mapper
public interface ManagerMapper extends BaseMapper<Manager>{
}
