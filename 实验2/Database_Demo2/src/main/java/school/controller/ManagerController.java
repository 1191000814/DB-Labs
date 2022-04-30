package school.controller;

import org.springframework.web.bind.annotation.*;
import school.entity.Manager;
import school.entity.Result;
import school.mapper.ManagerMapper;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class ManagerController extends BaseController<Manager>{

    @Resource
    private ManagerMapper mapper1;

    public ManagerController(){
        super(Manager.class, null);
    }

    @CrossOrigin // 跨域
    @GetMapping("/manager/selectAll")
    @Override
    public Result selectAll(){
        this.mapper = mapper1;
        return super.selectAll();
    }

    @CrossOrigin
    @GetMapping("/manager/delete/{id}")
    @Override
    public Result delete(@PathVariable("id") int id){
        this.mapper = mapper1;
        return super.delete(id);
    }

    @CrossOrigin
    @GetMapping("/manager/insert")
    @Override
    public Result insert(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.insert(map);
    }

    @CrossOrigin
    @GetMapping("/manager/update")
    @Override
    public Result update(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.update(map);
    }

}