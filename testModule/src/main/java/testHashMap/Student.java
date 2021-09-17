package testHashMap;

/**
 * @author sxh
 * @date 2021/9/16
 */
public class Student {
    private int stuNo;

    private int age;

    public Student(int stuNo, int age) {
        this.stuNo = stuNo;
        this.age = age;
    }

    public Student() {
    }

    public int getStuNo() {
        return stuNo;
    }

    public void setStuNo(int stuNo) {
        this.stuNo = stuNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 重写hashCode方法，覆盖Object默认的 [返回对象存储的内存地址的编号] 方法
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + stuNo;
        result = 31 * result + age;
        return result;
    }

    /**
     * 重写equals方法，覆盖Object [比较内存地址是否相等] 的方法
     *
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        // 地址相同，直接返回true
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Student)) {
            return false;
        }
        Student stu = (Student) obj;
        return stu.stuNo == stuNo && stu.age == age;
    }
}
