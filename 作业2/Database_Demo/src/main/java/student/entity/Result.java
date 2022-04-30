package student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// 后端返回给前端的数据
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result{

    private List<Student> students;
    private String sql;

}
