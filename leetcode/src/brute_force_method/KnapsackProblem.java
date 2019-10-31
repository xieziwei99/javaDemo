package brute_force_method;

import java.util.*;

/**
 * 0-1背包问题，蛮力法求解
 * @author xieziwei99
 * 2019-10-25
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        // 可选物品
        List<Map.Entry<Integer, Integer>> weightValues = new ArrayList<>();
        weightValues.add(new AbstractMap.SimpleEntry<>(7, 42));
        weightValues.add(new AbstractMap.SimpleEntry<>(3, 12));
        weightValues.add(new AbstractMap.SimpleEntry<>(4, 40));
        weightValues.add(new AbstractMap.SimpleEntry<>(5, 25));
        int thingsNum = weightValues.size();
        int capacity = 10;      // 背包容量
        int maxValue = 0;       // 最优解
        int flag = 0b1111;
        int maxFlag = flag;
        char[] flagChars;
        while (flag != 0) {
            int weight = 0, value = 0;
            flagChars = String.format("%4s", Integer.toBinaryString(flag)).
                    replace(' ', '0').toCharArray();
            for (int i = 0; i < flagChars.length; i++) {
                if ('1' == flagChars[i]) {
                    value += weightValues.get(i).getValue();
                    weight += weightValues.get(i).getKey();
                }
            }
            if (weight <= capacity && value > maxValue) {
                maxValue = value;
                maxFlag = flag;
            }
            flag -= 0b1;
        }
        System.out.println("最大价值为：" + maxValue);
        System.out.println("拿取物品：");
        flagChars = String.format("%4s", Integer.toBinaryString(maxFlag)).
                replace(' ', '0').toCharArray();
        for (int i = 0; i < flagChars.length; i++) {
            if ('1' == flagChars[i]) {
                System.out.println("\t(" + weightValues.get(i).getKey()
                        + ", " + weightValues.get(i).getValue() + ")");
            }
        }
    }
}
