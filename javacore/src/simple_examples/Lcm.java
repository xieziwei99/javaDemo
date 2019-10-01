package simple_examples;

import java.util.HashMap;

/**
 * least-common-multiple 求解两个数的最小公倍数
 * @author xieziwei99
 * 2019-10-01
 */
public class Lcm {

    /**
     * 求出一个数x的因子
     * @param x 传入的整数
     * @return 哈希表<因子，因子的个数>，例如 12 -> <2, 2> <3, 1>
     */
    public static HashMap<Integer, Integer> factorization(int x) {
        HashMap<Integer, Integer> ret = new HashMap<>();
        int i = 2;
        while (i <= x) {
            if (0 == x % i) {
                // 有3个参数，param1：键key，param2：新值value，param3：lambda函数
                // 如果ret中不存在i，则将新值1关联给i，若存在i，则将旧值与新值计算后关联给i
                ret.merge(i, 1, Integer::sum);
                x /= i;
            } else {
                i++;
            }
        }
        return ret;
    }

    /**
     * 计算两个数的最小公倍数
     * @param a 数a
     * @param b 数b
     * @return int
     */
    public static int lcm(int a, int b) throws Exception {
        HashMap<Integer, Integer> aa = factorization(a);
        HashMap<Integer, Integer> bb = factorization(b);
        bb.keySet().forEach(key -> aa.merge(key, bb.get(key), Math::max));
        return aa.entrySet().stream().map(entry -> (int) Math.pow(entry.getKey(),
                entry.getValue())).reduce((left, right) -> left * right).orElseThrow(Exception::new);
    }

    public static void main(String[] args) throws Exception {
        int a = 6;
        int b = 15;
        System.out.println(lcm(a, b));
    }
}
