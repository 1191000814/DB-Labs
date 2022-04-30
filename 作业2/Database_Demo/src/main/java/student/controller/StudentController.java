package student.controller;

import org.springframework.web.bind.annotation.*;
import student.entity.Params;
import student.entity.Result;
import student.entity.Student;
import student.mapper.StudentMapper;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController{

    @Resource
    StudentMapper studentMapper;

    // 改进后的获取学生信息
    @CrossOrigin
    @GetMapping("/getStudent")
    public Result get(@RequestParam Map<String, String> map){
        System.out.println("前端传来的参数: " + map);
        // 打印下参数信息
        Student student = new Student();
        student.setId(Integer.parseInt(map.get("id")));
        student.setName(map.get("name"));
        student.setClassNo(Integer.parseInt(map.get("classNo")));
        student.setSex(map.get("sex"));
        student.setDept(map.get("dept"));
        student.setAddress(map.get("address"));
        int minAge = Integer.parseInt(map.get("minAge"));
        int maxAge = Integer.parseInt(map.get("maxAge"));
        String[] hasParamsStr = map.get("hasParams").split(",");
        boolean[] hasParams = new boolean[7];
        for(int i = 0; i < 7; i++)
            hasParams[i] = Boolean.parseBoolean(hasParamsStr[i]); // 字符串转化为布尔值

        System.out.println("参数有效性: " + Arrays.toString(hasParams));
        // 下面是有没有这个字段
        // 构造sql语句
        StringBuilder sql = new StringBuilder();
        sql.append("select * from student where ");
        for(int i = 0; i < hasParams.length; i ++){
            if(hasParams[i]){
                switch(i){
                    case 0: sql.append("id = ").append(student.getId()).append(" and ");break;
                    case 1: sql.append("name like '%").append(student.getName()).append("%' and ");break;
                    case 2: sql.append("age >= ").append(minAge).append(" and ").append("age <= ").append(maxAge).append(" and ");break;
                    case 3: sql.append("sex = '").append(student.getSex()).append("' and ");break;
                    case 4: sql.append("classNo = ").append(student.getClassNo()).append(" and ");break;
                    case 5: sql.append("dept = '").append(student.getDept()).append("' and ");break;
                    case 6: sql.append("address like '%").append(student.getAddress()).append("%'");break;
                }
            }
        }

        if(sql.toString().endsWith("where ")) // 如果没有条件, 把where去掉
            sql = new StringBuilder(sql.substring(0, sql.length() - 6));
        if(sql.toString().endsWith("and ")) // 如果只有一个条件, 把and去掉
            sql = new StringBuilder(sql.substring(0, sql.length() - 4));

        Params params = new Params(); // 接收参数的类, 里面有学生, 哪些参数存在, 最大/小年龄
        params.setStudent(student);
        params.setHasParams(hasParams);
        params.setMaxAge(maxAge);
        params.setMinAge(minAge);

        System.out.println("前端传来的参数:" + params);

        List<Student> studentList = studentMapper.getStudent(params);
        Result result = new Result();
        result.setStudents(studentList);
        result.setSql(sql + ";");
        System.out.println("给前端回传的参数: " + result);

        // 留出最后一个对象专门用来传递sql语句
        return result;
    }
}