package scheduler;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Use of ScheduleExecutorService
 * @author xieziwei99
 * 2019-06-08
 */
public class ScheduleExecutorTest implements Runnable{

    private final String jobName;

    public ScheduleExecutorTest(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println("Start running => " + jobName + " at " + new Date().toString());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        // start after 1s, and do every 1s
//        executorService.scheduleAtFixedRate(new ScheduleExecutorTest("job1"), 1, 1, TimeUnit.SECONDS);
        // start after 1s, and do next after 2s
        executorService.scheduleWithFixedDelay(new ScheduleExecutorTest("job2"), 1, 2, TimeUnit.SECONDS);
    }
}
