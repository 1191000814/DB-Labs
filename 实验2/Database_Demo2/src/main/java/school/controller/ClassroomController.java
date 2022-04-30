package school.controller;

import org.springframework.web.bind.annotation.*;
import school.entity.Classroom;
import school.entity.Result;
import school.entity.Classroom;
import school.mapper.ClassroomMapper;
import school.mapper.ClassroomMapper;
import school.utils.SchoolUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class ClassroomController extends BaseController<Classroom>{

    @Resource
    private ClassroomMapper mapper1;

    public ClassroomController(){
        super(Classroom.class, null);
    }

    @CrossOrigin // 跨域
    @GetMapping("/classroom/selectAll")
    @Override
    public Result selectAll(){
        this.mapper = mapper1;
        return super.selectAll();
    }

    @CrossOrigin
    @GetMapping("/classroom/delete/{id}")
    @Override
    public Result delete(@PathVariable("id") int id){
        this.mapper = mapper1;
        return super.delete(id);
    }

    @CrossOrigin
    @GetMapping("/classroom/insert")
    @Override
    public Result insert(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.insert(map);
    }

    @CrossOrigin
    @GetMapping("/classroom/update")
    @Override
    public Result update(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.update(map);
    }
}