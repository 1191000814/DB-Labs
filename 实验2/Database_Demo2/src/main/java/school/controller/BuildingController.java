package school.controller;

import org.springframework.web.bind.annotation.*;
import school.entity.Result;
import school.entity.Building;
import school.mapper.BuildingMapper;

import javax.annotation.Resource;
import java.util.Map;

@RestController
public class BuildingController extends BaseController<Building>{

    @Resource
    private BuildingMapper mapper1;

    public BuildingController(){
        super(Building.class, null);
    }

    @CrossOrigin // 跨域
    @GetMapping("/building/selectAll")
    @Override
    public Result selectAll(){
        this.mapper = mapper1;
        return super.selectAll();
    }

    @CrossOrigin
    @GetMapping("/building/delete/{id}")
    @Override
    public Result delete(@PathVariable("id") int id){
        this.mapper = mapper1;
        return super.delete(id);
    }

    @CrossOrigin
    @GetMapping("/building/insert")
    @Override
    public Result insert(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.insert(map);
    }

    @CrossOrigin
    @GetMapping("/building/update")
    @Override
    public Result update(@RequestParam Map<String, String> map){
        this.mapper = mapper1;
        return super.update(map);
    }

}