package school.controller;

import org.springframework.web.bind.annotation.*;
import school.entity.Result;
import school.entity.Teacher;
import school.mapper.TeacherMapper;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class TeacherController extends BaseController<Teacher>{

    @Resource
    private TeacherMapper mapper1;

    public TeacherController(){
        super(Teacher.class, null);
    }

    @CrossOrigin // 跨域
    @GetMapping("/teacher/selectAll")
    @Override
    public Result selectAll(){
        this.mapper = mapper1;
        return super.selectAll();
    }

    @CrossOrigin
    @GetMapping("/teacher/delete/{id}")
    @Override
    public Result delete(@PathVariable("id") int id){
        this.mapper = mapper1;
        return super.delete(id);
    }

    @CrossOrigin
    @GetMapping("/teacher/insert")
    @Override
    public Result insert(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.insert(map);
    }

    @CrossOrigin
    @GetMapping("/teacher/update")
    @Override
    public Result update(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.update(map);
    }
}