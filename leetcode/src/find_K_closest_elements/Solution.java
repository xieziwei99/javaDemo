package find_K_closest_elements;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 从给定数组中找到 k 个和已知数 x 最接近的数
 * @author xieziwei99
 * 2019-10-12
 */
public class Solution {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int k = 4, x = 3;
        System.out.println(new Solution().findClosestElements(arr, k, x));
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 将arr按照到x的距离升序排序
        List<Integer> ret = IntStream.of(arr).boxed().sorted(
                Comparator.comparingInt(a -> Math.abs(a - x))).collect(Collectors.toList());
        // 取出前k个
        ret = ret.subList(0, k);
        ret.sort(Comparator.naturalOrder());
        return ret;
    }
}
