package branchbounding;

import java.util.*;

/**
 * 0/1背包问题-分支限界法
 * @author xieziwei99
 * 2019-11-15
 */
public class KnapsackProblem {
    private static final int capacity = 10;      // 背包容量
    private static List<Thing> things = new ArrayList<>();
    private static List<ElementOfPT> PT = new LinkedList<>();
    private static int up, down;
    static {
        things.add(new Thing(7, 42));
        things.add(new Thing(3, 12));
        things.add(new Thing(4, 40));
        things.add(new Thing(5, 25));

        things.sort((o1, o2) -> o2.getPrice() - o1.getPrice()); // 按性价比从大到小排序
        up = things.get(0).getPrice() * capacity;   // 确定上界
        // 贪心法求得下界，每次拿取性价比最高的物品
        for (int i = 0, currentWeight = 0; i < things.size(); i++) {
            Thing thing = things.get(i);
            if (currentWeight + thing.weight <= capacity) {
                down += thing.value;
                currentWeight += thing.weight;
            }
        }
    }

    // 得到当前拿取所有物品的weight和value
    private static Thing getTotalWeightAndValue(List<Integer> current) {
        Thing thing = new Thing(0, 0);
        for (int i = 0; i < current.size(); i++) {
            if (current.get(i) == 1) {   // 已拿取的物品
                thing.weight += things.get(i).weight;
                thing.value += things.get(i).value;
            }
        }
        return thing;
    }

    public static void main(String[] args) {
        PT.add(new ElementOfPT(up, new ArrayList<>()));
        int i = Collections.max(PT).currentThings.size();
        int ub;
        while (i < things.size()) {     // 当i=4时，表明已经考虑了4个物品且当前ub最大
            List<Integer> currentThings = Collections.max(PT).currentThings;
            PT.remove(Collections.max(PT));
            Thing currentWeightAndValue = getTotalWeightAndValue(currentThings);
            // 不拿当前物品
            if (i + 1 < 4) {
                ub = currentWeightAndValue.value + (capacity - currentWeightAndValue.weight) * things.get(i + 1).getPrice();
            } else {
                ub = currentWeightAndValue.value;
            }
            if (ub >= down) {
                List<Integer> newThings = new ArrayList<>(currentThings);
                newThings.add(0);
                PT.add(new ElementOfPT(ub, newThings));
                if (newThings.size() == things.size()) {
                    down = ub;
                }
            }
            // 拿取当前物品
            if (currentWeightAndValue.weight + things.get(i).weight <= capacity) {
                List<Integer> newThings = new ArrayList<>(currentThings);
                newThings.add(1);
                currentWeightAndValue = getTotalWeightAndValue(newThings);
                if (i + 1 < 4) {
                    ub = currentWeightAndValue.value + (capacity - currentWeightAndValue.weight) * things.get(i + 1).getPrice();
                } else {
                    ub = currentWeightAndValue.value;
                }
                if (ub >= down) {
                    PT.add(new ElementOfPT(ub, newThings));
                    if (newThings.size() == things.size()) {
                        down = ub;
                    }
                }
            }
            i = Collections.max(PT).currentThings.size();
        }

        System.out.println("最大价值为：" + Collections.max(PT).ub);
        System.out.print("拿取物品：");
        for (int i1 = 0; i1 < Collections.max(PT).currentThings.size(); i1++) {
            if (Collections.max(PT).currentThings.get(i1) == 1) {   // 拿取的物品
                System.out.print('\t' + things.get(i1).toString());
            }
        }
    }
}

class Thing {
    int weight;
    int value;

    public Thing() {
    }

    public Thing(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getPrice() {
        return value / weight;
    }

    @Override
    public String toString() {
        return "Thing{" +
                "weight=" + weight +
                ", value=" + value +
                '}';
    }
}

class ElementOfPT implements Comparable {
    int ub; // 存储当前的ub值
    List<Integer> currentThings = new ArrayList<>();    // 拿取/未拿取序列。[1,0] [1,0,1,0]

    public ElementOfPT() {
    }

    public ElementOfPT(int ub, List<Integer> currentThings) {
        this.ub = ub;
        this.currentThings = currentThings;
    }

    @Override
    public int compareTo(Object o) {
        return this.ub - ((ElementOfPT)o).ub;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ElementOfPT that = (ElementOfPT) o;
        return ub == that.ub &&
                Objects.equals(currentThings, that.currentThings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ub, currentThings);
    }
}