package base;

/**
 * 关于浅拷贝和深拷贝
 * @author xieziwei99
 * 2019-06-05
 */
public class Copy {

    public static void main(String[] args) throws CloneNotSupportedException {
        Family family = new Family();
        family.setName("没变");
        Family family1 = (Family) family.clone();
        family1.setName("没有使他改变");
        System.out.println(family.getName());

        Student student = new Student();
        student.setFamily(family);

        Student student1 = (Student) student.clone();
        student1.getFamily().setName("变了");
        // 这里，在浅拷贝下，student的family的name字段也被改变了
        // 在深拷贝下，student的family的name字段不会改变，仍然是“没变”
        System.out.println(student.getFamily().getName());
        System.out.println(student1.getFamily().getName());
    }
}

class Family implements Cloneable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Student implements Cloneable {
    private String name;
    private Family family;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    // 浅拷贝
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }


//    // 深拷贝
//    @Override
//    protected Object clone() throws CloneNotSupportedException {
//        Student s;
//        s = (Student) super.clone();
//        s.family = (Family) this.family.clone();
//        return s;
//    }
}
