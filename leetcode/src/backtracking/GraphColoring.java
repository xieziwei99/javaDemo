package backtracking;

import java.util.Arrays;

/**
 * 图着色问题-回溯法
 * @author xieziwei99
 * 2019-11-08
 */
public class GraphColoring {
    private static int[][] graph = {
            {-1, 1, 1, 0, 0},
            {1, -1, 1, 1, 1},
            {1, 1, -1, 0, 1},
            {0, 1, 0, -1, 1},
            {0, 1, 1, 1, -1}
    };
    private static int[] graphColors = {0, 0, 0, 0, 0};
    private static final int colorNum = 3;

    // 判断顶点k的着色是否发生冲突
    private static boolean isOk(int k) {
        for (int i = 0; i < graph[k].length; i++) {
            if (graph[k][i] == 1 && graphColors[i] == graphColors[k]) {     // 连通且着色相同
                return false;
            }
        }
        return true;
    }

    public static void coloring() {
        int k = 0;
        while (k >= 0) {
            graphColors[k]++;   // 为顶点k涂上颜色
            while (graphColors[k] <= colorNum) {    // 也可能graphColors[k] > colorNum时停止循环
                if (isOk(k)) {
                    break;      // 若着色不冲突，停止
                } else {
                    graphColors[k]++;   // 冲突，着下一个颜色
                }
            }
            // 如果所用颜色数不超过所给颜色数，且都涂上了颜色
            if (graphColors[k] <= colorNum && k == graphColors.length - 1) {
                System.out.println(Arrays.toString(graphColors));
                return;
            } else if (graphColors[k] <= colorNum && k < graphColors.length - 1) {
                k++;    // 顶点没有涂满，涂下一个
            } else {
                graphColors[k] = 0; // 颜色数用完
                k--;    // 回溯
            }
        }
    }

    public static void main(String[] args) {
        coloring();
    }
}
