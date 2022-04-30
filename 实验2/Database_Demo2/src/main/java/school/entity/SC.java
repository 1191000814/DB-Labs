package school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SC{

    private Integer sId;
    private Integer cId;
    private Integer score;

    public boolean validated(){
        return score >= 0 && score <= 100;
    }
}
