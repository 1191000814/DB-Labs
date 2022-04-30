package school.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 后端返回给前端的数据
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result{

    private boolean flag; // 后端返回给前端的标志符号,若非正常执行则返回false
    private Object data; // 若flag为true,则返回查询数据(若无数据返回null),若flag为false则返回错误信息

}
