package school.front;

import lombok.Data;
import lombok.NoArgsConstructor;
import school.entity.Student;

@Data
@NoArgsConstructor
//@EqualsAndHashCode(callSuper = false)
public class FrontStudent extends Student{

    public FrontStudent(Integer id, String name, Integer age, Integer deptId, String deptName){
        super(id, name, age, deptId);
        this.deptName = deptName;
    }

    private String deptName;
}