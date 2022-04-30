package school.controller;

import org.springframework.web.bind.annotation.*;
import school.entity.Result;
import school.entity.Student;
import school.front.FrontStudent;
import school.mapper.StudentMapper;
import school.utils.SchoolUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController extends BaseController<Student>{
    @Resource
    private StudentMapper mapper1;

    public StudentController(){
        super(Student.class, null);
    }

    @CrossOrigin // 跨域
    @GetMapping("/student/selectAll")
    @Override
    public Result selectAll(){
        this.mapper = mapper1;
        return super.selectAll();
    }

    @CrossOrigin // 跨域
    @GetMapping("/student/selectAll2/{group}")
    public Result selectAll2(@PathVariable("group") int group){
        SchoolUtils.myPrint("查询全部|详细信息");
        List<FrontStudent> students = mapper1.selectAll2(group);
        if(students.size() == 0)
            return new Result(false, SchoolUtils.selectNullError);
        return new Result(true, students);
    }

    @CrossOrigin
    @GetMapping("/student/delete/{id}")
    @Override
    public Result delete(@PathVariable("id") int id){
        this.mapper = mapper1;
        return super.delete(id);
    }

    @CrossOrigin
    @GetMapping("/student/insert")
    @Override
    public Result insert(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.insert(map);
    }

    @CrossOrigin
    @GetMapping("/student/update")
    @Override
    public Result update(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.update(map);
    }
}