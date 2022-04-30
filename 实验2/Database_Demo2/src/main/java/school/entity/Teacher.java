package school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher{

    private Integer id;
    private String name;
    private Integer deptId;
    private Integer salary;

    public boolean validated(){
        return id > 0 && salary > 0;
    }
}
