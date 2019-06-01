package collection.list;

import java.util.*;

/**
 * ArrayList和Vector简单使用示例
 * @author xieziwei99
 * 2019-05-23
 */
public class Demo1 {

    public static void main(String[] args) {
        listTest();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        printLine();
        try {
            wrongIteratorTest();
        } catch (ConcurrentModificationException e) {
            System.err.println("错误使用：在创建迭代器之后，除非通过迭代器自生的remove或add方法从结构上对列表进行修改," +
                    "否则其他修改都会抛出异常：ConcurrentModificationException");
        }
        printLine();
        correctIteratorTest();
        printLine();
        vectorTest();
        printLine();
    }

    private static void listTest() {
        List<String> a1 = new ArrayList<>();
        a1.add("dev1");
        a1.add("dev3");
        a1.add("dev4");
        System.out.println("原始集合：" + a1);

        a1.add(1, "dev2");
        System.out.println("指定index=1插入：" + a1);

        a1.remove(2);
        System.out.println("指定index=2移除：" + a1);

        System.out.println("指定index=2查询" + a1.get(2));

        System.out.print("用迭代器遍历全部元素：");
        Iterator ite = a1.iterator();
        while (ite.hasNext()) {
            System.out.print(ite.next() + ", ");
        }
        System.out.println();
    }

    // 错误使用：在创建迭代器之后，除非通过迭代器自生的remove或add方法从结构上对列表进行修改，
    // 否则其他修改都会抛出异常：ConcurrentModificationException
    private static void wrongIteratorTest() {
        List<String> a1 = new ArrayList<>();
        a1.add("dev1");
        a1.add("dev2");
        a1.add("dev4");
        a1.add("dev5");
        Iterator ite = a1.iterator();
        while (ite.hasNext()) {
            Object obj = ite.next();
            if ("dev2".equals(obj)) {
                a1.add("dev3");
            }
        }
        System.out.println("elements in List: " + a1);
    }

    private static void correctIteratorTest() {
        List<String> a1 = new ArrayList<>();
        a1.add("dev1");
        a1.add("dev");
        a1.add("dev3");
        a1.add("dev4");

        ListIterator<String> ite = a1.listIterator();
        while (ite.hasNext()) {
            Object obj = ite.next();
            if ("dev".equals(obj)) {
                ite.set("dev2");
            }
        }
        System.out.println("Elements in List: " + a1);
    }

    private static void vectorTest() {
        Vector<String> v1 = new Vector<>();
        v1.add("dev1");
        v1.add("dev2");
        v1.add("dev3");
        v1.add("dev4");
        System.out.print("用Enumeration遍历Vector：");
        Enumeration<String> enu = v1.elements();
        while (enu.hasMoreElements()) {
            System.out.print(enu.nextElement() + ", ");
        }
        System.out.println();

        System.out.print("用Iterator遍历Vector：");
        Iterator<String> ite = v1.listIterator();
        while (ite.hasNext()) {
            System.out.print(ite.next() + ", ");
        }
        System.out.println();
    }

    private static void printLine() {
        System.out.println("--------------------------------------------------");
    }
}
