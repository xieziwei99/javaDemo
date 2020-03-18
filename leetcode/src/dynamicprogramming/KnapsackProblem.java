package dynamicprogramming;

import java.util.Arrays;

/**
 * 0/1背包问题-动态规划法
 * @author xieziwei99
 * 2019-12-13
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        int[] weights = {2, 2, 6, 5, 4};
        int[] values = {6, 3, 5, 4, 6};
        int capacity = 10;
        int[] take = new int[weights.length];
        // opt[i][j]表示把前i个物品装入容量为j的背包中获得的最大价值
        // 所以i：前i个物品，j：容量，值：最大价值
        int[][] opt = new int[weights.length + 1][capacity + 1];
        for (int i = 0; i < opt.length; i++) {
            opt[i][0] = 0;
        }
        Arrays.fill(opt[0], 0);
        for (int i = 1; i < opt.length; i++) {
            for (int j = 1; j < opt[i].length; j++) {
                if (j < weights[i-1]) {   // 装不下当前物品
                    opt[i][j] = opt[i-1][j];
                } else {
                    opt[i][j] = Math.max(values[i-1] + opt[i-1][j-weights[i-1]], opt[i-1][j]);
                }
            }
        }

        int j = capacity;
        for (int i = opt.length - 1; i > 0; i--) {
            if (opt[i][j] > opt[i - 1][j]) {
                take[i-1] = 1;
                j -= weights[i-1];
            } else {
                take[i-1] = 0;
            }
        }

        System.out.println("最大价值为：" + opt[opt.length-1][opt[0].length - 1]);
        System.out.println("拿取物品：");
        for (int i = 0; i < take.length; i++) {
            if (1 == take[i]) {
                System.out.print("\t(" + weights[i] + ", " + values[i] + ")");
            }
        }
    }
}
