package school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Building{

    private Integer id;
    private String name;
    private Integer mId;

    public boolean validated(){
        return id > 0 && mId > 0;
    }
}
