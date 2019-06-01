/**
 * @Description 正确的使用日期格式
 * @author xieziwei99
 * @create 2019-05-14
 */
package simpleDateFormat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Correct {

    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String formatDate(LocalDateTime dateTime) {
        return formatter.format(dateTime);
    }

    public static LocalDateTime parse(String strDate) {
        return LocalDateTime.parse(strDate, formatter);
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService service = Executors.newFixedThreadPool(100);

        for (int i = 0; i < 20; i++) {
            service.execute(() -> {
                for (int j = 0; j < 10; j++) {
                    // 输出无异常
                    System.out.println(parse("2019-05-14 17:44:31"));
                }
            });
        }

        service.shutdown();
        service.awaitTermination(1, TimeUnit.DAYS);

        System.out.println(LocalDateTime.now());
        System.out.println(formatDate(LocalDateTime.now()));
    }
}
