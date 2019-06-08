package scheduler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Use of Timer
 * @author xieziwei99
 * 2019-06-08
 */
public class TimerTest extends TimerTask {

    private final String jobName;

    public TimerTest(String jobName) {
        this.jobName = jobName;
    }

    @Override
    public void run() {
        System.out.println("run the task: " + this.jobName);
    }

    public static void main(String[] args) {
        Timer timer = new Timer();
        // 6s后开始执行，每1s执行一次
        timer.schedule(new TimerTest("job 1"), 6000, 1000);
        // 2s后开始执行，每2s执行一次
        timer.schedule(new TimerTest("job 2"), 2000, 2000);
    }
}
