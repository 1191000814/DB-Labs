package school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import school.entity.Dept;

@Repository
@Mapper
public interface DeptMapper extends BaseMapper<Dept>{
}