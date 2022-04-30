package school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 前端给后端传的数据
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Params<T>{

    private T obj;
    private boolean[] hasParams;
    private int minAge;
    private int maxAge;
}
