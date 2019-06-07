package lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * lambda操作符
 * @author xieziwei99
 * 2019-06-06
 */
public class Demo1 {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("cccc");
        list.add("b");
        list.add("eeeee");

        list.sort(String::compareToIgnoreCase);
        list.forEach(System.out::println);

        list.sort(Comparator.comparing(String::length));
        list.forEach(System.out::println);
    }
}
