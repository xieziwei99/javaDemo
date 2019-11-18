package backtracking;

/**
 * 回溯法求解 0/1背包问题
 *
 * @author xieziwei99
 * 2019-11-08
 */
public class KnapsackProblem {
    private static final int capacity = 10;      // 背包容量
    private static int maxValue = 0;       // 最优解
    private static int[] weights = {7, 3, 4, 5};
    private static int[] values = {42, 12, 40, 25};
    private static int thingNum = weights.length;
    private static int currentWeight = 0;
    private static int currentValue = 0;
    private static int[] flag = {0, 0, 0, 0};
    private static int[] maxFlag = flag.clone();

    public static void main(String[] args) {
        getMaxValue(0);
        System.out.println("最大价值为：" + maxValue);
        System.out.println("拿取物品：");
        for (int i = 0; i < maxFlag.length; i++) {
            if (1 == maxFlag[i]) {
                System.out.print("\t(" + weights[i] + ", " + values[i] + ")");
            }
        }
    }

    private static void getMaxValue(int i) {
        if (i >= thingNum) {
            if (currentValue > maxValue) {
                maxValue = currentValue;
                maxFlag = flag.clone();
            }
            return;
        }
        if (currentWeight + weights[i] <= capacity) {   // 物品i加入
            flag[i] = 1;
            currentWeight += weights[i];
            currentValue += values[i];
            getMaxValue(i + 1);     // 考虑下一个物品是否加入
            // 回溯
            currentWeight -= weights[i];
            currentValue -= values[i];
            flag[i] = 0;
        }
        getMaxValue(i + 1);     // 考虑下一个物品是否加入
    }
}
