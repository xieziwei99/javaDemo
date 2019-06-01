package test;

/**
 * @author xieziwei99
 * 2019-05-25
 */
public class StringInMemory {

    public static void main(String[] args) {
        String s1 = "hello";    // 这种方法，存放在字符串常量池
        String s2 = "hello";
        String s3 = new String("hello");    // 这种方法，存放在堆内存

        System.out.println(s1 == s2);   // true
        System.out.println(s1 == s3);   // false

        String s4 = s3.intern();    // 手动将其值转移到字符串常量池中
        System.out.println(s1 == s3);   // false s3不会改变
        System.out.println(s1 == s4);   // true
    }
}
