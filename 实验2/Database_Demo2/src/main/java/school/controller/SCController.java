package school.controller;

import org.springframework.web.bind.annotation.*;
import school.entity.SC;
import school.entity.Result;
import school.mapper.SCMapper;
import school.utils.SchoolUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
public class SCController{

    @Resource
    private SCMapper mapper;

    @CrossOrigin
    @GetMapping("/sc/selectAll2/{group}")
    public Result selectAll(@PathVariable("group") int group){
        List<SC> scs = mapper.selectAll2(group);
        if(scs.size() == 0)
            return new Result(false, SchoolUtils.selectNullError);
        SchoolUtils.myPrint("查询:" + scs);
        return new Result(true, scs);
    }

    @CrossOrigin
    @GetMapping("/sc/delete/{sId}/{cId}")
    public Result delete(@PathVariable("sId") int sId, @PathVariable("cId") int cId){
        try{
            mapper.deleteById(sId, cId);
        }catch(Exception e){
            return new Result(false, SchoolUtils.foreignKeyError);
        }
        return new Result(true, null);
    }

    @CrossOrigin
    @GetMapping("/sc/insert")
    public Result insert(@RequestParam Map<String, String> map){
        SchoolUtils.myPrint("新增: " + map);
        int sId = Integer.parseInt(map.get("sId"));
        int cId = Integer.parseInt(map.get("cId"));
        if(mapper.selectById(sId, cId) != null)
            return new Result(false, SchoolUtils.primaryKeyExisted);
        SC sc = new SC(sId, cId, Integer.parseInt(map.get("score")));
        if(! sc.validated())
            return new Result(false, SchoolUtils.userDefinedError);
        try{
            mapper.insert(sc);
        }catch(Exception e){
            System.out.println(e.toString());
            return new Result(false, SchoolUtils.foreignKeyError);
        }
        return new Result(true, null);
    }

    @CrossOrigin
    @GetMapping("/sc/update")
    public Result update(@RequestParam Map<String, String>  map){
        SchoolUtils.myPrint(map);
        int sId = Integer.parseInt(map.get("sId"));
        int cId = Integer.parseInt(map.get("cId"));
        if(mapper.selectById(sId, cId) == null)
            return new Result(false, SchoolUtils.primaryKeyNotExisted);
        SC sc = new SC(sId, cId, Integer.parseInt(map.get("score")));
        if(! sc.validated())
            return new Result(false, SchoolUtils.userDefinedError);
        try{
            mapper.update(sc);
        }catch(Exception e){
            System.out.println(e.toString());
            return new Result(false, SchoolUtils.foreignKeyError);
        }
        return new Result(true, null);
    }

}