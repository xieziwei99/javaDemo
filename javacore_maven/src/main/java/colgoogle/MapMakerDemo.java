package colgoogle;

import com.google.common.base.Function;
import com.google.common.collect.MapMaker;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * google的MapMaker工具
 * @author xieziwei99
 * 2019-06-06
 */
public class MapMakerDemo {

    public static void main(String[] args) {
        // softKeys：键在判断时用 == 判断
        // weakValues：键在判断时用 == 判断，且若没有对象引用，则立即被GC回收
        // expiration：设置超时时间是3秒，3秒过后，自动从map中删除每一个entry
        ConcurrentMap<String, String> map = new MapMaker().concurrencyLevel(32).softKeys().weakValues()
                .expiration(3, TimeUnit.SECONDS).makeComputingMap(
                        // 这提供了当Map中不包含所get的项时，自动加入到map的功能，且返回值为对应key的value
                        new Function<String, String>() {
                            @Override
                            public String apply(String from) {
                                return "creating " + from + "-> Object";
                            }
                        }
                );
        map.put("a", "testA");
        map.put("b", "testB");
        System.out.println(map.get("a"));
        System.out.println(map.get("b"));
        System.out.println(map.get("c"));

        // 5s后，大于超时时间，缓存实效
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(map.get("a"));
        System.out.println(map.get("b"));
        System.out.println(map.get("c"));
    }
}
