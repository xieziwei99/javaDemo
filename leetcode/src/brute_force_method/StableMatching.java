package brute_force_method;

import java.util.*;

/**
 * 稳定婚姻匹配问题：求婚-拒绝算法
 * @author xieziwei99
 * 2019-10-25
 */
public class StableMatching {
    // 男士喜好表
    private static List<String> V = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
    private static List<String> W = new ArrayList<>(Arrays.asList("B", "C", "D", "A", "E"));
    private static List<String> X = new ArrayList<>(Arrays.asList("C", "D", "A", "B", "E"));
    private static List<String> Y = new ArrayList<>(Arrays.asList("D", "A", "B", "C", "E"));
    private static List<String> Z = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E"));
    private static Map<String, List<String>> menFavour = new HashMap<>();

    // 女士喜好表
    private static List<String> A = new ArrayList<>(Arrays.asList("W", "X", "Y", "Z", "V"));
    private static List<String> B = new ArrayList<>(Arrays.asList("X", "Y", "Z", "V", "W"));
    private static List<String> C = new ArrayList<>(Arrays.asList("Y", "Z", "V", "W", "X"));
    private static List<String> D = new ArrayList<>(Arrays.asList("Z", "V", "W", "X", "Y"));
    private static List<String> E = new ArrayList<>(Arrays.asList("V", "W", "X", "Y", "Z"));
    private static Map<String, List<String>> womenFavour = new HashMap<>();

    private static Map<String, String> stableMatch = new HashMap<>();  // 记录匹配的Map<女士, 男士>
    private static Stack<String> lostMen = new Stack<>();  // 被女方抛弃的男士

    // 在woman心中，man1优于man2时为true
    private static boolean womanComp(String woman, String man1, String man2) {
        int i1 = womenFavour.get(woman).indexOf(man1);
        int i2 = womenFavour.get(woman).indexOf(man2);
        return i1 < i2;
    }

    private static void manProposeToWomen(String man, String woman) {
        if (stableMatch.get(woman) == null) {
            stableMatch.put(woman, man);
        } else {
            if (womanComp(woman, man, stableMatch.get(woman))) {
                lostMen.push(stableMatch.get(woman));   // 当前男士被抛弃
                stableMatch.put(woman, man);    // woman选择trades up
            } else {
                lostMen.push(man);  // 男士求婚失败
            }
        }
    }

    public static void main(String[] args) {
        menFavour.put("V", V);      menFavour.put("W", W);
        menFavour.put("X", X);      menFavour.put("Y", Y);
        menFavour.put("Z", Z);      womenFavour.put("A", A);
        womenFavour.put("B", B);    womenFavour.put("C", C);
        womenFavour.put("D", D);    womenFavour.put("E", E);

        menFavour.forEach((man, manFavour) -> {
            // 男士向列表中第一个女士求婚，求过婚后移除，没有第二次求婚
            manProposeToWomen(man, manFavour.remove(0));
        });

        while (!lostMen.empty()) {
            String man = lostMen.pop();
            manProposeToWomen(man, menFavour.get(man).remove(0));
        }
        System.out.println("稳定的匹配是：");
        stableMatch.forEach((woman, man) -> System.out.print("(" + man + ", " + woman + ")\t"));
    }
}