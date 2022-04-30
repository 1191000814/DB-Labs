package school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  //getter 和 setter
@AllArgsConstructor //全参构造器
@NoArgsConstructor //无参构造器
public class Student{   //每一个实体对应一张表,每个属性对应表中一列

    private Integer id;
    private String name;
    private Integer age;
    private Integer deptId;

    public boolean validated(){  //用户自定义约束
        return id >= Math.pow(10, 9) && id < Math.pow(10, 10) && age > 0 && age < 100;
    }

}
