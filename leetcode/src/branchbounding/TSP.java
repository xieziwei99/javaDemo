package branchbounding;

import java.util.*;

/**
 * TSP问题-分支限界法
 *
 * @author xieziwei99
 * 2019-11-15
 */
public class TSP {
    private static int[][] tsp = {
            {Integer.MAX_VALUE, 3, 1, 5, 8},    // 顶点0
            {3, Integer.MAX_VALUE, 6, 7, 9},    // 顶点1
            {1, 6, Integer.MAX_VALUE, 4, 2},    // 顶点2
            {5, 7, 4, Integer.MAX_VALUE, 3},    // 顶点3
            {8, 9, 2, 3, Integer.MAX_VALUE}     // 顶点4
    };
    private static List<ElementsOfPT> PT = new LinkedList<>();
    private static int up = 0, down = getLb(new ArrayList<>());

    static {
        // 用贪心法求得TSP问题的一个近似解，作为上界
        List<Integer> bestPath = new ArrayList<>();
        bestPath.add(0);
        while (bestPath.size() < tsp.length) {
            int start = bestPath.get(bestPath.size()-1);
            int least = Integer.MAX_VALUE, index = 0;
            for (int i = 0; i < tsp[start].length; i++) {
                if (!bestPath.contains(i) && tsp[start][i] < least) {
                    least = tsp[start][i];
                    index = i;
                }
            }
            up += least;
            bestPath.add(index);
        }
        up += tsp[bestPath.get(bestPath.size()-1)][0];
    }

    // 得到数组中最小的两个数的和
    private static int getSumOfLeastTwo(int[] a) {
        int least1 = Math.min(a[0], a[1]), least2 = Math.max(a[0], a[1]);
        for (int i = 2; i < a.length; i++) {
            if (a[i] < least1) {
                least2 = least1;
                least1 = a[i];
            } else {
                least2 = Math.min(least2, a[i]);
            }
        }
        return least1 + least2;
    }

    // 得到数组中最小的一个数和一个指定的数(该数是数组中的数)的和
    private static int getSumOfLeastTwo(int[] a, int specified) {
        int least = Integer.MAX_VALUE;
        for (int value : a) {
            if (value != specified) {
                least = Math.min(least, value);
            }
        }
        return least + specified;
    }

    // 在给定的路径下，得到tsp矩阵的理想下界。路径可以是[], [0, 3, 4]等
    private static int getLb(List<Integer> path) {
        int lb = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            lb += getSumOfLeastTwo(tsp[path.get(i)], tsp[path.get(i)][path.get(i + 1)]);
        }
        for (int i = 0; i < tsp.length; i++) {
            if (!path.contains(i)) {
                lb += getSumOfLeastTwo(tsp[i]);
            }
        }
        if (path.size() == tsp.length) {
            lb += getSumOfLeastTwo(tsp[path.get(path.size() - 1)],
                    tsp[path.get(path.size() - 1)][path.get(0)]);
        }
        if (path.size() != 0) {
            lb += getSumOfLeastTwo(tsp[path.get(path.size() - 1)]);
        }
        return (int) Math.ceil((double) lb / 2);
    }

    private static void addPathToPT(List<Integer> currentPath) {
        int totalPoint = tsp.length;    // 总结点数有5个，0,1,2...totalPoint-1
        for (int i = 0; i < totalPoint; i++) {
            if (!currentPath.contains(i)) {
                currentPath.add(i);
                int currentLb = getLb(currentPath);
                if (currentLb <= up) {
                    PT.add(new ElementsOfPT(currentLb, new ArrayList<>(currentPath)));
                    if (currentPath.size() == tsp.length) {
                        up = currentLb; // 更新上界
                    }
                }
                currentPath.remove((Integer) i);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> currentPath = new ArrayList<>();
        currentPath.add(0); // 从0号结点出发
        PT.add(new ElementsOfPT(down, currentPath));
        while (Collections.min(PT).currentPath.size() < tsp.length) {    // 当路径中有5个时，结束
            currentPath = Collections.min(PT).currentPath;
            PT.remove(Collections.min(PT));

            addPathToPT(currentPath);
        }

        ElementsOfPT best = Collections.min(PT);
        System.out.println("最小代价为：" + best.lb);
        System.out.print("路径为：");
        for (Integer p : best.currentPath) {
            System.out.print((p) + " -> ");
        }
        System.out.println(0);  // 从0出发回到0
    }
}

class ElementsOfPT implements Comparable {
    int lb; // 存储当前的lb值
    List<Integer> currentPath = new ArrayList<>();    // 当前选择的路径[0, 2, 4]

    public ElementsOfPT() {
    }

    public ElementsOfPT(int lb, List<Integer> currentPath) {
        this.lb = lb;
        this.currentPath = currentPath;
    }

    @Override
    public int compareTo(Object o) {
        return this.lb - ((ElementsOfPT) o).lb;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ElementsOfPT that = (ElementsOfPT) o;
        return lb == that.lb &&
                Objects.equals(currentPath, that.currentPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lb, currentPath);
    }
}