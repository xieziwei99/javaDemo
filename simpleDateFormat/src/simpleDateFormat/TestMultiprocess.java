/**
 * @Description 多线程测试SimpleDateFormat
 * @author xieziwei99
 * @create 2019-05-14
 */
package simpleDateFormat;

import java.text.ParseException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestMultiprocess {

    public static void main(String[] args) throws InterruptedException {
        
        ExecutorService service = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 20; i++) {
            service.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    try {
                        // 此时会引发异常，或者输出时间错误
                        // 原因是SimpleDateFormat的实例sdf会被多个线程共享
                        System.out.println(Test.parse("2019-5-14 17:44:31"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);
    }
}
