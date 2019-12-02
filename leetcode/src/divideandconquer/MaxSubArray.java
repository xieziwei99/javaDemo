package divideandconquer;

import java.util.Arrays;

/**
 * 最大字段和-分治法
 * @author xzw
 * 2019-11-29
 */
public class MaxSubArray {

    private static int getMaxSub(int[] a, int left, int right) {
        if (left >= right) {
            return Math.max(a[left], 0);
        }
        int mid = (left + right) / 2;
        int maxLeft = getMaxSub(a, mid + 1, right);
        int maxRight = getMaxSub(a, left, mid - 1);
        int maxMidLeft = 0, maxMidRight = 0;
        for (int i = mid - 1, temp = 0; i >= left; i--) {
            temp += a[i];
            if (temp > maxMidLeft) {
                maxMidLeft = temp;
            }
        }
        for (int i = mid, temp = 0; i <= right; i++) {
            temp += a[i];
            if (temp > maxMidRight) {
                maxMidRight = temp;
            }
        }
        return Math.max((maxMidLeft + maxMidRight), Math.max(maxLeft, maxRight));
    }

    public static int getMaxSub(int[] a) {
        boolean allMinus = true;
        for (int i : a) {
            if (i > 0) {
                allMinus = false;
                break;
            }
        }
        if (allMinus) {
            return Arrays.stream(a).max().getAsInt();
        } else {
            return getMaxSub(a, 0, a.length - 1);
        }
    }

    public static void main(String[] args) {
        int[] a = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
//        int[] a = {-1, -3, -5};
        System.out.println("最大子段和为：" + getMaxSub(a));
    }
}
