package student.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student{

    private int id;
    private String name;
    private int age;
    private String sex;
    private int classNo;
    private String dept;
    private String address;

}
