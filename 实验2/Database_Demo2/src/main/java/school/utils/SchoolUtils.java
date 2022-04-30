package school.utils;

import java.lang.reflect.Field;

public class SchoolUtils{

    public static String primaryKeyExisted = "主键已经存在或者非法";
    public static String primaryKeyNotExisted = "主键不存在";
    public static String foreignKeyError = "存在外键约束,无法删除,修改或新增";
    public static String userDefinedError = "变量值域非法";
    public static String selectNullError = "查询结果为空";

    public static void myPrint(Object object){
        System.out.println("\033[34;1m" + object + "\033[0m\n");
    }

    public static Object getFieldValue(Object o, String fieldName) throws NoSuchFieldException, IllegalAccessException{
        Class<?> cls = o.getClass();
        Field field = cls.getDeclaredField(fieldName);
        field.setAccessible(true);
//        if(field.getType().equals(Integer.class))
//            return field.getInt(o);
//        else if(field.getType().equals(Float.class))
//            return field.getFloat(o);
//        else
//            return field.get(o);
        return field.get(o);
    }
}