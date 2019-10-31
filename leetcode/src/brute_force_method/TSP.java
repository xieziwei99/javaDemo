package brute_force_method;

import java.util.ArrayList;
import java.util.List;

/**
 * TSP问题，蛮力法求解
 *
 * @author xieziwei99
 * 2019-10-25
 */
public class TSP {
    private static <T> void swap(T[] arr, int k, int i) {
        T temp = arr[k];
        arr[k] = arr[i];
        arr[i] = temp;
    }

    public static <T> void permutation(T[] arr, int k, int length, List<T[]> result) {
        //只有一个数，则排列确定，将该数组保存在集合中
        if (k == length - 1) {
            T[] b = arr.clone();
            result.add(b);
        } else {
            for (int i = k; i < length; i++) {
                swap(arr, k, i);
                permutation(arr, k + 1, length, result);
                swap(arr, k, i);
            }
        }
    }

    public static void main(String[] args) {
        final int INFINITY = Integer.MAX_VALUE;
        int[][] tsp = {{INFINITY, 3, 6, 7}, {12, INFINITY, 2, 8}, {8, 6, INFINITY, 2}, {3, 7, 6,
                INFINITY}};
        int minPrice = INFINITY;    // 最小代价
        int minFlag = 0;    // 标记最小代价时的路径
        Integer[] perm = {0, 1, 2, 3};
        List<Integer[]> permResult = new ArrayList<>();
        permutation(perm, 0, perm.length, permResult);
        for (int i = 0; i < permResult.size(); i++) {   // i指代24种排列中的一种
            Integer[] arr = permResult.get(i);          // 如arr=[0, 1, 2, 3]
            int price = 0;
            int j;
            for (j = 0; j < arr.length - 1; j++) {
                price += tsp[arr[j]][arr[j+1]];
            }
            price += tsp[arr[j]][arr[0]];
            if (price < minPrice) {
                minPrice = price;
                minFlag = i;
            }
        }
        System.out.println("最小代价为：" + minPrice);
        System.out.print("路径为：");
        for (Integer integer : permResult.get(minFlag)) {
            System.out.print(integer + " -> ");
        }
        System.out.println(permResult.get(minFlag)[0]);
    }
}
