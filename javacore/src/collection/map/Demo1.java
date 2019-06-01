package collection.map;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回空Map，则使用Collections.EMPTY_MAP
 * @author xieziwei99
 * 2019-06-01
 */
public class Demo1 {

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        long endTime;
        long duration;
        for (int i = 0; i < 100000000; i++) {
            Map map = Collections.EMPTY_MAP;
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("Collections.EMPTY_MAP:  " + duration);

        startTime = System.nanoTime();
        for (int i = 0; i < 100000000; i++) {
            Map map = new HashMap<>();
        }
        endTime = System.nanoTime();
        duration = endTime - startTime;
        System.out.println("new HashMap:  " + duration);
    }
}
