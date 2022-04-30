package school.controller;

import org.springframework.web.bind.annotation.*;
import school.entity.Course;
import school.entity.Result;
import school.mapper.CourseMapper;

import javax.annotation.Resource;

import java.util.Map;

@RestController
public class CourseController extends BaseController<Course>{

    @Resource
    private CourseMapper mapper1;

    public CourseController(){
        super(Course.class, null);
    }

    @CrossOrigin // 跨域
    @GetMapping("/course/selectAll")
    @Override
    public Result selectAll(){
        this.mapper = mapper1;
        return super.selectAll();
    }

    @CrossOrigin
    @GetMapping("/course/delete/{id}")
    @Override
    public Result delete(@PathVariable("id") int id){
        this.mapper = mapper1;
        return super.delete(id);
    }

    @CrossOrigin
    @GetMapping("/course/insert")
    @Override
    public Result insert(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.insert(map);
    }

    @CrossOrigin
    @GetMapping("/course/update")
    @Override
    public Result update(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.update(map);
    }

}