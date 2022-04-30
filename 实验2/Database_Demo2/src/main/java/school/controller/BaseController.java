package school.controller;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.RequestParam;
import school.entity.Result;
import school.mapper.BaseMapper;
import school.utils.ParserParams;
import school.utils.SchoolUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
public abstract class BaseController<T>{
    protected final Class<?> cls;
    protected BaseMapper<T> mapper;
    protected final ParserParams<T> parser;

    public BaseController(Class<?> cls, BaseMapper<T> mapper){
        this.cls = cls;
        this.mapper = mapper;
        parser = new ParserParams<>(cls);
    }

    public Result selectAll(){
        SchoolUtils.myPrint("查询全部");
        List<T> tList = mapper.selectAll();
        if(tList.size() == 0)
            return new Result(false, SchoolUtils.selectNullError);
        return new Result(true, tList);
    }

    public Result selectAll2(){
        SchoolUtils.myPrint("查询全部|详细信息");
        List<T> tList = mapper.selectAll2();
        if(tList.size() == 0)
            return new Result(false, SchoolUtils.selectNullError);
        return new Result(true, tList);
    }

    public Result delete(int id){   // @PathVariable表示url路径中传来的参数,将前端的id赋值给后端的id
        try{
            SchoolUtils.myPrint("删除:" + id);
            if(mapper.selectById(id) == null)
                return new Result(false, SchoolUtils.primaryKeyNotExisted);
            mapper.deleteById(id);
        }catch(Exception e){
            return new Result(false, SchoolUtils.foreignKeyError);
        }
        return new Result(true, null);
    }

    // 有两个主键的删除
    public Result delete(int id1, int id2){
        try{
            SchoolUtils.myPrint("删除:" + id1 + ", " + id2);
            if(mapper.selectById(id1, id2) == null)
                return new Result(false, SchoolUtils.primaryKeyNotExisted);
            mapper.deleteById(id1, id2);
        }catch(Exception e){
            return new Result(false, SchoolUtils.foreignKeyError);
        }
        return new Result(true, null);
    }

    public Result insert(Map<String, String> map){  //@RequestParam表示前端给后端传送参数
        try{
            T o = parser.parser(map);
            SchoolUtils.myPrint("新增:" + o);
            if(mapper.selectById((int) SchoolUtils.getFieldValue(o, "id")) != null) // 已有此项
                return new Result(false, SchoolUtils.primaryKeyExisted);
            if(! (Boolean) cls.getMethod("validated").invoke(o)) // 属性值非法
                return new Result(false, SchoolUtils.userDefinedError);
            mapper.insert(o);
        } catch(NumberFormatException e){
            return new Result(false, SchoolUtils.userDefinedError);
        } catch(DataIntegrityViolationException e){
            return new Result(false, SchoolUtils.foreignKeyError);
        } catch(NoSuchFieldException | InvocationTargetException | IllegalAccessException | NoSuchMethodException |
            InstantiationException e){
            SchoolUtils.myPrint("程序出错");
            throw new RuntimeException(e);
        }
        return new Result(true, null);
    }

    public Result update(@RequestParam Map<String, String> map){
        try{
            T o = parser.parser(map);
            SchoolUtils.myPrint("更新:" + o);
            if(mapper.selectById((int) SchoolUtils.getFieldValue(o, "id")) == null) // 没有此项
                return new Result(false, SchoolUtils.primaryKeyNotExisted);
            if(! (Boolean) cls.getMethod("validated").invoke(o)) // 属性值非法
                return new Result(false, SchoolUtils.userDefinedError);
            mapper.update(o);
        }catch(InvocationTargetException | IllegalAccessException | NoSuchMethodException | InstantiationException |
               NoSuchFieldException e){
            return new Result(false, SchoolUtils.foreignKeyError);
        }catch(DataIntegrityViolationException e){
            return new Result(false, SchoolUtils.userDefinedError);
        }
        return new Result(true, null);
    }

}