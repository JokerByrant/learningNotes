package testHashMap;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * HashMap中key放的是一个对象
 * 对应的面试题：HashMap key是一个类，需要注意什么？
 *
 * @author sxh
 * @date 2021/9/16
 */
public class TestHashMap {
    /**
     * Student对象重写了equals和hashcode方法
     * 新的equals如果两个对象的值一样，也返回true
     */
    @Test
    public void overrideEqualsAndHashCode() {
        Map<Student, String> map = new HashMap<Student, String>();
        Student stu1 = new Student(1, 20);
        Student stu2 = new Student(2, 19);
        map.put(stu1, "stu1");
        map.put(stu2, "stu2");
        System.out.println(map.get(stu1));
        System.out.println(map.get(stu2));

        // stu3和stu1的参数值一致，但是地址并不一样，仍能拿到key=stu1的value
        Student stu3 = new Student(1, 20);
        System.out.println(map.get(stu3));
    }

    /**
     * 常规调用，未重写equals和hashcode
     */
    @Test
    public void ordinaryInvoke() {
        Map<Teacher, String> map = new HashMap<Teacher, String>();
        Teacher teacher1 = new Teacher(1, 20);
        Teacher teacher2 = new Teacher(2, 19);
        map.put(teacher1, "teacher1");
        map.put(teacher2, "teacher2");
        System.out.println(map.get(teacher1));
        System.out.println(map.get(teacher2));

        // teacher3和teacher1的参数值一致，但是地址并不一样，不能拿到key=teacher1的value
        Teacher teacher3 = new Teacher(1, 20);
        System.out.println(map.get(teacher3));
    }
}
