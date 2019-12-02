package divideandconquer;

import java.util.Scanner;

/**
 * 分治法-第k小问题（元素选择问题）
 *
 * @author xzw
 * 2019-11-29
 */
public class LeastK {

    // 快速排序中一个划分过程，划分得到的枢轴就是该元素最终排序的位置，不再变化
    private static int partition(int[] a, int[] index, int left, int right) {
        int temp = a[left];     // 作为中间枢轴
        int tempIndex = index[left];
        while (left < right) {
            // 从右向左，找到第一个比中间枢轴小的，移到left位置
            while (left < right && a[right] >= temp) {
                right--;
            }
            a[left] = a[right];
            index[left] = index[right];
            // 从右向左，找到第一个比中间数轴大的，移到right位置
            while (left < right && a[left] <= temp) {
                left++;
            }
            a[right] = a[left];
            index[right] = index[left];
        }
        a[left] = temp;
        index[left] = tempIndex;
        return left;
    }

    private static int[] selectLeastK(int[] a, int[] index, int left, int right, int k) {
        int[] ret = new int[2];
        int pivot = partition(a, index, left, right);
        if (pivot == k - 1) {
            ret[0] = a[k - 1];
            ret[1] = index[k - 1];
            return ret;
        } else if (pivot > k - 1) {
            return selectLeastK(a, index, left, pivot - 1, k);
        } else {
            return selectLeastK(a, index, pivot + 1, right, k);
        }
    }

    public static void main(String[] args) {
        int[] a = {5, 2, 7, 4, 0, 6, 8, 3, 9, 1};
        int[] index = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please input k = ");
        int k = scanner.nextInt();
        scanner.close();
        if (k > 0 && k <= a.length) {
            int[] leastK = selectLeastK(a, index, 0, a.length - 1, k);
            System.out.println("最小元素为：" + leastK[0]);
            System.out.println("索引为：" + leastK[1]);
        } else {
            System.out.println("k的输入不合理");
        }
    }
}
