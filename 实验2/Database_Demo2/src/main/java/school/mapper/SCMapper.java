package school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import school.entity.SC;

import java.util.List;

@Repository
@Mapper
public interface SCMapper extends BaseMapper<SC>{

    SC selectById(int sId, int cId);

    List<SC> selectAll2(int group);

    boolean deleteById(int sId, int cId);
}