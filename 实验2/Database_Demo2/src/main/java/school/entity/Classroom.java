package school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Classroom{

    private Integer id; // 教室id
    private Integer bId; // 教学楼号
    private Integer clNum; // 教室在楼中的编号(非主键)

    public boolean validated(){
        return id > 0 && clNum < 1200;
    }
}