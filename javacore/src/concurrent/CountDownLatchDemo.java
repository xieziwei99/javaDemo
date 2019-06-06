package concurrent;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 同步辅助类CountDownLatch，在一组线程执行完之前，使得一个或多个线程一直等待
 * @author xieziwei99
 * 2019-06-06
 */
public class CountDownLatchDemo {

    private final static int COUNT = 10;
    private final static CountDownLatch count = new CountDownLatch(COUNT);

    private final static ExecutorService service = Executors.newFixedThreadPool(5);

    public static void main(String[] args) {
        for (int i = 0; i < COUNT; i++) {
            service.execute(() -> {
                int time = new Random().nextInt(3);
                try {
                    TimeUnit.SECONDS.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.printf("Thread %s --- 耗时：%d\n", Thread.currentThread().getName(), time);
                count.countDown();  // 若计数器不为0，则计数器减一
            });
        }

        try {
            count.await();  // 当count.countDown()减为0时，开始运行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        service.shutdown();
        System.out.println("同步线程执行组结束");
    }
}
