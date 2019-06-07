package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * 利用反射处理对象的构造函数、方法、字段
 * @author xieziwei99
 * 2019-06-07
 */
@SuppressWarnings("FieldCanBeLocal")
public class ReflectionTest {
    private String name;
    private int age;

    public ReflectionTest() {
        this(null, 0);
    }

    public ReflectionTest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        Class clazz = null;
        try {
            clazz = Class.forName("reflection.ReflectionTest");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Print the Constructors: ");
        assert clazz != null;
        printCtors(clazz);
        System.out.println("Print methods: ");
        printMethods(clazz);
        System.out.println("Print fields: ");
        printFields(clazz);
    }

    public static void printCtors(Class clazz) {
        Constructor[] ctors = clazz.getDeclaredConstructors();  // 如果是默认构造函数，也会返回
        for (Constructor c : ctors) {
            String name = c.getName();
            System.out.print("    ");
            String modifiers = Modifier.toString(c.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(name + "(");

            // 获取参数对象列表
            Class[] paramTypes = c.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    public static void printMethods(Class clazz) {
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            Class retType = method.getReturnType();
            String name = method.getName();

            System.out.print("\t");
            String modifiers = Modifier.toString(method.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.print(retType.getName() + " " + name + "(");

            Class[] paramTypes = method.getParameterTypes();
            for (int i = 0; i < paramTypes.length; i++) {
                if (i > 0) {
                    System.out.print(", ");
                }
                System.out.print(paramTypes[i].getName());
            }
            System.out.println(");");
        }
    }

    public static void printFields(Class clazz) {
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            Class type = field.getType();
            String name = field.getName();
            System.out.print("\t");
            String modifiers = Modifier.toString(field.getModifiers());
            if (modifiers.length() > 0) {
                System.out.print(modifiers + " ");
            }
            System.out.println(type.getName() + " " + name);
        }
    }
}
