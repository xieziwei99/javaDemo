package backtracking;

import java.util.Scanner;

/**
 * 警卫问题-回溯法
 *
 * @author xieziwei99
 * 2019-11-14
 */
public class GuardProblem {
    private static int m, n;
    private static int[][] placeGuard;
    private static int[][] guarded;
    private static int guardsNum = 0;
    private static int leastGuardNum;
    private static int[][] bestPlaced;      // 警卫最少的情况

    static {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入行数m=");
        m = scanner.nextInt();
        System.out.print("请输入列数n=");
        n = scanner.nextInt();
        scanner.close();
        placeGuard = new int[m][n];        // 放置警卫矩阵，默认初始全为0
        guarded = new int[m][n];   // 房间是否被监视，默认初始全为0
        bestPlaced = new int[m][n];
        leastGuardNum = 17;
    }

    private static void addGuard(int x, int y) {
        guardsNum++;
        placeGuard[x][y] = 1;
        guarded[x][y]++;
        if (x - 1 >= 0) {   // 上
            guarded[x - 1][y]++;
        }
        if (x + 1 < m) {    // 下
            guarded[x + 1][y]++;
        }
        if (y - 1 >= 0) {   // 左
            guarded[x][y - 1]++;
        }
        if (y + 1 < n) {    // 右
            guarded[x][y + 1]++;
        }
    }

    private static void withdrawGuard(int x, int y) {
        guardsNum--;
        placeGuard[x][y] = 0;
        guarded[x][y]--;
        if (x - 1 >= 0 && guarded[x - 1][y] > 0) {
            guarded[x - 1][y]--;
        }
        if (x + 1 < m && guarded[x + 1][y] > 0) {
            guarded[x + 1][y]--;
        }
        if (y - 1 >= 0 && guarded[x][y - 1] > 0) {
            guarded[x][y - 1]--;
        }
        if (y + 1 < n && guarded[x][y + 1] > 0) {
            guarded[x][y + 1]--;
        }
    }

    // 全都有监视
    private static boolean allGuarded() {
        for (int[] row : guarded) {
            for (int r : row) {
                if (r == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static void search(int x, int y) {
        if (x == m - 1 && y == n - 1) {
            if (allGuarded()) {
//                System.out.println("一个可能解为：");
//                for (int[] row : placeGuard) {
//                    for (int b : row) {
//                        System.out.print(b + " ");
//                    }
//                    System.out.println();
//                }
                if (guardsNum < leastGuardNum) {
                    leastGuardNum = guardsNum;
                    for (int i = 0; i < bestPlaced.length; i++) {
                        bestPlaced[i] = placeGuard[i].clone();
                    }
                }
            }
            return;
        }
        if (0 == guarded[x][y]) {   // 没有警卫监视
            if (x + 1 < m) {
                addGuard(x + 1, y);     // 往下放
                if (y + 1 < n && guardsNum < leastGuardNum) {
                    search(x, y + 1);   // 向右查找
                } else if (x + 1 < m && guardsNum < leastGuardNum) {
                    search(x + 1, 0);   // 向下换一行继续查找
                }
                withdrawGuard(x + 1, y);    // 回溯
            }

            addGuard(x, y);            // 原处放
            if (y + 1 < n && guardsNum < leastGuardNum) {
                search(x, y + 1);   // 向右查找
            } else if (x + 1 < m && guardsNum < leastGuardNum) {
                search(x + 1, 0);   // 向下换一行继续查找
            }
            withdrawGuard(x, y);    // 回溯

            if (y + 1 < n) {
                addGuard(x, y + 1);     // 往右放
                if (y + 1 < n && guardsNum < leastGuardNum) {
                    search(x, y + 1);   // 向右查找
                } else if (x + 1 < m && guardsNum < leastGuardNum) {
                    search(x + 1, 0);   // 向下换一行继续查找
                }
                withdrawGuard(x, y + 1);    // 回溯
            }
        }
        if (y + 1 < n && guardsNum < leastGuardNum) {
            search(x, y + 1);   // 向右查找
        } else if (x + 1 < m && guardsNum < leastGuardNum) {
            search(x + 1, 0);   // 向下换一行继续查找
        }
    }

    public static void main(String[] args) {
        search(0, 0);
        System.out.println("最少的警卫机器人数为：" + leastGuardNum);
        for (int[] row : bestPlaced) {
            for (int b : row) {
                System.out.print(b + " ");
            }
            System.out.println();
        }
    }
}