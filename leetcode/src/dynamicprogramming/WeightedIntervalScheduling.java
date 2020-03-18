package dynamicprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 带权区间调度问题-动态规划法
 *
 * @author xieziwei99
 * 2019-12-12
 */
public class WeightedIntervalScheduling {

    public static void main(String[] args) {
        List<Job> jobList = new ArrayList<>(Arrays.asList(
                new Job(0, 4, 2), new Job(1, 6, 4),
                new Job(5, 7, 4), new Job(2, 9, 7),
                new Job(8, 10, 2), new Job(8, 10, 1)));

        jobList.sort(Comparator.comparingInt(j -> j.endPoint));

        // 用p[j]存储在列表中与活动j相容的上一个活动的下标i
        // 例如本题中：p[1] = 0, p[2] = 0, p[3] = 1, p[4] = 0, p[5] = 3, p[6] = 3; p[0]不存储
        int[] p = new int[jobList.size() + 1];
        for (int i = 0; i < jobList.size(); i++) {
            int temp = i;
            while (temp >= 0) {
                temp--;
                if (temp >= 0 && jobList.get(i).isCompatibleTo(jobList.get(temp))) {
                    p[i + 1] = temp + 1;
                    break;
                } else {
                    p[i + 1] = temp + 1;
                }
            }
        }

        // 用opt[j]存储第j号时的最优值
        int[] opt = new int[jobList.size() + 1];
        opt[0] = 0;
        List<Job>[] optJobs = new ArrayList[jobList.size() + 1];
        optJobs[0] = new ArrayList<>();
        for (int i = 1; i < opt.length; i++) {
            if (jobList.get(i - 1).value + opt[p[i]] >= opt[i - 1]) {
                opt[i] = jobList.get(i-1).value + opt[p[i]];
                optJobs[i] = new ArrayList<>(optJobs[p[i]]);
                optJobs[i].add(jobList.get(i-1));
            } else {
                opt[i] = opt[i - 1];
                optJobs[i] = new ArrayList<>(optJobs[i - 1]);
            }
        }

        System.out.println("互相兼容的区间的最大权值为:" + opt[opt.length - 1]);
        System.out.println("区间为:" + optJobs[optJobs.length - 1]);
    }
}

class Job {
    int startPoint;
    int endPoint;
    int value;

    public Job(int startPoint, int endPoint, int value) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.value = value;
    }

    public boolean isCompatibleTo(Job other) {
        return this.endPoint <= other.startPoint || other.endPoint <= this.startPoint;
    }

    @Override
    public String toString() {
        return "(" + startPoint + ", " + endPoint + ", " + value + ')';
    }
}