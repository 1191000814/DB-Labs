package student.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import student.entity.Params;
import student.entity.Student;

import java.util.List;

@Repository
@Mapper
public interface StudentMapper{

    List<Student> getStudent(Params params);
}