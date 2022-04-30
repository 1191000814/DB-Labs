package school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import school.entity.Building;

@Repository
@Mapper
public interface BuildingMapper extends BaseMapper<Building>{
}
