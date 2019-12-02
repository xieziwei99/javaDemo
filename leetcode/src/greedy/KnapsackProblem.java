package greedy;

import java.util.ArrayList;
import java.util.List;

/**
 * 贪心法-0/1背包问题
 * @author xzw
 * 2019-11-29
 */
public class KnapsackProblem {

    public static void main(String[] args) {
        int capacity = 10, maxValue = 0;
        List<Thing> things = new ArrayList<>();
        things.add(new Thing(7, 42));
        things.add(new Thing(3, 12));
        things.add(new Thing(4, 40));
        things.add(new Thing(5, 25));
        // 按单价从大到小排序
        things.sort((o1, o2) -> o2.getPrice() - o1.getPrice());

        for (int i = 0, currentWeight = 0; i < things.size(); i++) {
            Thing thing = things.get(i);
            if (currentWeight + thing.weight <= capacity) {
                maxValue += thing.value;
                currentWeight += thing.weight;
                System.out.println("拿取物品：" + thing.toString());
            }
        }
        System.out.println("贪心法求得最大价值为：" + maxValue);
    }
}

class Thing {
    int weight;
    int value;

    Thing(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    int getPrice() {
        return value / weight;
    }

    @Override
    public String toString() {
        return "Thing{weight=" + weight + ", value=" + value + '}';
    }
}