package reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 利用反射通过构造器创建实例
 * @author xieziwei99
 * 2019-06-07
 */
public class UserConstructorReflect {
    public static void main(String[] args) {
        Class<?> userClazz = User.class;
        try {
            Constructor<?> ctor = userClazz.getConstructor();
            Constructor<?> intCtor = userClazz.getConstructor(int.class);
            Constructor<?> bothCtor = userClazz.getConstructor(int.class, String.class);

            User user = (User) ctor.newInstance();
            User user1 = (User) intCtor.newInstance(100);
            User user2  = (User) bothCtor.newInstance(10, "tsy");

            System.out.println(user.toString());
            System.out.println(user1.toString());
            System.out.println(user2.toString());
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

class User {
    private int id;
    private String name;

    public User() {
        this(0, null);
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
