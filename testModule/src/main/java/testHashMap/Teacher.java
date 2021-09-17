package testHashMap;

/**
 * @author sxh
 * @date 2021/9/16
 */
public class Teacher {
    private int techNo;

    private int age;

    public Teacher(int techNo, int age) {
        this.techNo = techNo;
        this.age = age;
    }

    public Teacher() {
    }

    public int getTechNo() {
        return techNo;
    }

    public void setTechNo(int techNo) {
        this.techNo = techNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
