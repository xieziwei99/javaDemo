package lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * lambda 表达式实例
 * @author xieziwei99
 * 2019-08-06
 */
public class Demo2 {

    public static void checkAndExecute(List<Person> people, NameCheck nameCheck, Executor executor) {
        for (Person p : people) {
            if (nameCheck.check(p)) {
                executor.execute(p);
            }
        }
    }

    // 直接使用java.util.function中定义好了的函数式接口Predicate和Consumer，与上自己定义的功能一样
    public static void checkAndExecute2(List<Person> people, Predicate<Person> predicate, Consumer<Person> consumer) {
        people.forEach(person -> {
            if (predicate.test(person)) {
                consumer.accept(person);
            }
        });
    }

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(Arrays.asList(
                new Person("Zhang", "San", 18),
                new Person("Li", "Si", 19),
                new Person("Wang", "Wu", 20),
                new Person("Qian", "Liu", 21)
        ));
        checkAndExecute(people, p -> p.getLastName().startsWith("S"), System.out::println);
        checkAndExecute2(people, p -> p.getLastName().startsWith("S"), System.out::println);
        // 用stream代替上面的foreach
        people.stream().filter(person -> person.getLastName().startsWith("S"))
                .forEach(System.out::println);
    }
}


class Person {
    private String firstName;
    private String lastName;
    private int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                '}';
    }
}

// 函数式接口
@FunctionalInterface
interface NameCheck {
    boolean check(Person p);
}

@FunctionalInterface
interface Executor {
    void execute(Person p);
}