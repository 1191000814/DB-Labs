package school;

import org.junit.Test;
import school.entity.Student;
import school.utils.ParserParams;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ParserTest{

    ParserParams<Student> parserParams = new ParserParams<>(Student.class);

    @Test
    public void testParser() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException{
        Map<String, String> map = new HashMap<>();
        map.put("name", "wlw");
        map.put("age", "20");
        map.put("id", "1");
        Student parser = parserParams.parser(map);
        System.out.println(parser);
    }

    @Test
    public void test1() throws NoSuchFieldException, IllegalAccessException{
        Student student = new Student(1, "znma", 12, 1);
        Class<Student> studentClass = Student.class;
        Field field = studentClass.getDeclaredField("id");
        field.setAccessible(true);
        System.out.println(field.get(student));
    }
}
