package school.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BaseMapper<T>{

    // 五个自定义的函数名对应.xml文件的id
    T selectById(int id);
    T selectById(int id1, int id2);

    List<T> selectAll();

    List<T> selectAll2();

    boolean insert(T t);

    boolean deleteById(int id);

    boolean deleteById(int id1, int id2);

    boolean update(T t);
}