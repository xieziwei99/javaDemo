package reflection;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 利用反射扩容数组
 * @author xieziwei99
 * 2019-06-07
 */
public class ArrayCopy {

    public static void main(String[] args) {
        int[] a = {1,2,3};
        a = (int[]) goodCopyOf(a, 10);
        System.out.println(Arrays.toString(a));

        String[] str = {"a", "b", "c"};
        str = (String[]) goodCopyOf(str, 10);
        System.out.println(Arrays.toString(str));
    }

    /**
     * 扩容传入的数组并返回
     * @param o 原数组
     * @param newLength 新的长度
     * @return 与原数组前n个元素值相同，但大小是newLength的数组对象
     */
    private static Object goodCopyOf(Object o, int newLength) {
        Class c1 = o.getClass();
        // 如果o不是数组，则返回null
        if (!c1.isArray()) { return null; }
        Class componentType = c1.getComponentType();    // 返回数组中个体的类，如int，String
        int length = Array.getLength(o);
        Object newArray = Array.newInstance(componentType, newLength);
        System.arraycopy(o, 0, newArray, 0, Math.min(length, newLength));
        return newArray;
    }
}
