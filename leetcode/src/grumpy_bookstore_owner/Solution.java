package grumpy_bookstore_owner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 暴躁的书店老板
 * @author xieziwei99
 * 2019-10-18
 */
public class Solution {
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        int ret = 0;
        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < customers.length; i++) {
            temp.add(customers[i] * grumpy[i]);
            if (0 == grumpy[i]) {
                ret += customers[i];
            }
        }
        int maxTemp = 0;
        for (int i = 0; i < temp.size() - X + 1; i++) {
            Optional<Integer> reduce = temp.subList(i, i + X).stream().reduce(Integer::sum);
            if (reduce.isPresent()) {
                maxTemp = Math.max(maxTemp, reduce.get());
            }
        }
        return ret + maxTemp;
    }

    public static void main(String[] args) {
        int[] customers = {1,0,1,2,1,1,7,5};
        int[] grumpy = {0,1,0,1,0,1,0,1};
        int X = 3;
        System.out.println(new Solution().maxSatisfied(customers, grumpy, X));
    }
}
