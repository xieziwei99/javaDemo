package greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 区间调度问题(寻找最大的相融子集)-贪心法-可证明是最优解
 * @author xzw
 * 2019-11-29
 */
public class IntervalScheduling {

    public static void main(String[] args) {
        List<Job> jobList = new ArrayList<>(Arrays.asList(
                new Job(0, 6), new Job(1, 4), new Job(3, 5), new Job(3, 8),
                new Job(4, 7), new Job(5, 9), new Job(6, 10), new Job(8, 11)));
        jobList.sort(Comparator.comparingInt(o -> o.endPoint));
        List<Job> maxSubList = new ArrayList<>();
        for (int i = 0, startPoint = 0; i < jobList.size(); i++) {
            Job job = jobList.get(i);
            if (job.startPoint >= startPoint) {
                maxSubList.add(job);
                startPoint = job.endPoint;
            }
        }
        System.out.println("互相兼容的最大区间数为:" + maxSubList.size());
        System.out.println("区间为:" + maxSubList);
    }
}

class Job {
    int startPoint;
    int endPoint;

    public Job(int startPoint, int endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    @Override
    public String toString() {
        return "(" + startPoint + ", " + endPoint + ')';
    }
}