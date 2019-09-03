package meite.example.reflect;

import meite.example.json.User;

import java.lang.reflect.Field;

/**
 * 给私有属性赋值
 *
 * @author gavin
 * @date 2018/12/25 19:25
 */
public class TestReflect {
    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("meite.example.json.User");
        Object newInstance = clazz.newInstance();
        Field fieldId = clazz.getDeclaredField("id");
        /// 为私有属性赋值
//        fieldId.setAccessible(true);
        fieldId.set(newInstance, 20);
        User user = (User) newInstance;
        System.out.println(user);
    }
}
