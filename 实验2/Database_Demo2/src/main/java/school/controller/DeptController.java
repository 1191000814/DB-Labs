package school.controller;

import org.springframework.web.bind.annotation.*;
import school.entity.Dept;
import school.entity.Result;
import school.mapper.DeptMapper;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class DeptController extends BaseController<Dept>{

    @Resource
    private DeptMapper mapper1;

    public DeptController(){
        super(Dept.class, null);
    }

    @CrossOrigin // 跨域
    @GetMapping("/dept/selectAll")
    @Override
    public Result selectAll(){
        this.mapper = mapper1;
        return super.selectAll();
    }

    @CrossOrigin
    @GetMapping("/dept/delete/{id}")
    @Override
    public Result delete(@PathVariable("id") int id){
        this.mapper = mapper1;
        return super.delete(id);
    }

    @CrossOrigin
    @GetMapping("/dept/insert")
    @Override
    public Result insert(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.insert(map);
    }

    @CrossOrigin
    @GetMapping("/dept/update")
    @Override
    public Result update(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.update(map);
    }

}