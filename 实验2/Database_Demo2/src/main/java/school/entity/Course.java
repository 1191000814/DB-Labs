package school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Course{
    private Integer id;
    private String name;
    private Integer tId;
    private Float credit;
    private Integer clId;

    public boolean validated(){
        return id > 0 && credit > 0;
    }
}