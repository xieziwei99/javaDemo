package code_standards;

/**
 * 父类和子类的成员变量命名不应该相同
 * @author xieziwei99
 * 2019-05-23
 */
public class Parent {

    public String name = "parent";

    public static void main(String[] args) {
        Parent parent = new Child();
        Child child = new Child();

        System.out.println("name in Parent: " + parent.name);
        System.out.println("name in (Child)Parent: " + ((Child) parent).name);
        System.out.println("name in Child: " + child.name);
    }
}

class Child extends Parent{

    public String name = "child";
}
