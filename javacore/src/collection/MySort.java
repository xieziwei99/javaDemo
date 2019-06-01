package collection;

import java.util.Arrays;

/**
 * @author xieziwei99
 * 2019-06-01
 */
public class MySort {

    public static void main(String[] args) {
        int[] a = new int[]{2,1,3,4,6,7,5};
        // 三种复制数组的方法，效率：a1 > a2 > a3
        int[] a1 = new int[7];
        System.arraycopy(a, 0, a1, 0, a.length);
        int[] a2 = a.clone();
        int[] a3 = Arrays.copyOf(a, a.length);

        System.out.println("The origin: " + Arrays.toString(a));
        insertionSort(a);
        System.out.println("After insertion sort: " + Arrays.toString(a));

        quickSort(a1);
        System.out.println("After quick sort: " + Arrays.toString(a1));
        System.out.println("After insertion sort: " + Arrays.toString(a2));
        System.out.println("After insertion sort: " + Arrays.toString(a3));
    }

    public static void insertionSort(int[] a) {
        int p, right, temp;
        for (p = 0; p < a.length; p++) {
            temp = a[p];
            for (right=p; right>0 && a[right-1]>temp; right--) {
                a[right] = a[right - 1];
            }
            a[right] = temp;
        }
    }

    public static void quickSort(int[] a) {
        quickSort(a, 0, a.length - 1);
    }

    public static void quickSort(int[] a, int left, int right) {
        if (left < right) {
            int middle = quickSort_1(a, left, right);
            quickSort(a, left, middle-1);
            quickSort(a, middle+1, right);
        }
    }

    // 快速排序中一个划分过程
    public static int quickSort_1(int[] a, int left, int right) {
        int temp = a[left];     // 作为中间枢轴
        while (left < right) {
            // 从右向左，找到第一个比中间枢轴小的，移到left位置
            while (left < right && a[right] >= temp) {
                right--;
            }
            a[left] = a[right];
            // 从右向左，找到第一个比中间数轴大的，移到right位置
            while (left < right && a[left] <= temp) {
                left++;
            }
            a[right] = a[left];
        }
        a[left] = temp;
        return left;
    }
}
