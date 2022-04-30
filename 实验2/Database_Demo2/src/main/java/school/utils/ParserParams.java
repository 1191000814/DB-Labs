package school.utils;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;
import java.util.Map;

/**
 * 用来解析前端传来的参数, 对象已经泛型
 *
 * @param <T>
 */
@Data
public class ParserParams<T>{

    private Class<?> cls;

    public ParserParams(Class<?> cls){
        this.cls = cls;
    }

    public T parser(Map<String, String> map)
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException, InstantiationException{
        T o;
        o = (T) cls.newInstance();
        for(Field field : cls.getDeclaredFields()){
            String fieldName = field.getName();
            String value = map.get(fieldName);
            String setMethodName = "set" + fieldName.substring(0, 1).toUpperCase(Locale.ROOT) + fieldName.substring(1);
            // set的方法名(注意大小写)
            Method method = cls.getMethod(setMethodName, field.getType());
            // 获取方法
            if(field.getType().equals(Integer.class)) // 是一个整数
                method.invoke(o, Integer.parseInt(value));
            else if(field.getType().equals(Float.class)) // 是一个浮点数
                method.invoke(o, Float.parseFloat(value));
            else // 是一个字符串
                method.invoke(o, value);
            // 调用方法,在对象中设置对象
        }
        return (T) o;
    }

}