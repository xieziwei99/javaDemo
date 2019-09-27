package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;

/**
 * @author xieziwei99
 * 2019-09-27
 */
public class HashCodeAndEquals {

    public static void main(String[] args) {
        Person person = new Person("p1", 18);
        Person person1 = new Person("p1", 18);
        Person person2 = new Person("p2", 20);
        System.out.println("person == person1 ? " + person.equals(person1));
        System.out.println("person == person2 ? " + person.equals(person2));

        HashSet<Person> people = new HashSet<>(Arrays.asList(person, person1, person2));
        System.out.println("Elements in HashSet: ");
        people.forEach(System.out::println);
    }
}

class Person {
    private String name;
    private int age;

    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    // 若没有则HashSet中会含有3个元素，而不是2个
    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}