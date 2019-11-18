package backtracking;

import java.util.Scanner;

/**
 * 马的周游路线-回溯法
 *
 * @author xieziwei99
 * 2019-11-08
 */
public class KnightTour {
      private static int[][] board = new int[8][8];
//    private static int[][] board = {
//            {1, 2, 3, 4, 5, 6, 7, 8},
//            {9, 10, 11, 12, 13, 14, 15, 16},
//            {17, 18, 19, 20, 21, 22, 23, 24},
//            {25, 26, 27, 28, 29, 30, 31, 32},
//            {33, 34, 35, 36, 37, 38, 39, 40},
//            {41, 42, 43, 44, 45, 46, 47, 48},
//            {49, 50, 51, 52, 53, 54, 55, 56},
//            {57, 58, 59, 60, 61, 62, 63, 64}
//    };
    private static int size = board.length;
//    private static List<Integer> solution = new ArrayList<>();

    // 返回一条从(x, y)出发的解
    public static void patrol(int x, int y, int step) {
        if ((x >= 0 && x < size) && (y >= 0 && y < size) && board[x][y] == 0) {
//            solution.add(board[x][y]);
            board[x][y] = step;    // 置为0，表示马已走过
            if (step == size * size) {      // 64格走满了
                System.out.println("一条周游路线为：");
                for (int[] row : board) {
                    for (int r : row) {
                        System.out.print(r + " ");
                    }
                    System.out.println();
                }
                return;
            }
            patrol(x - 2, y - 1, step + 1);
            patrol(x - 1, y - 2, step + 1);
            patrol(x + 1, y - 2, step + 1);
            patrol(x + 2, y - 1, step + 1);
            patrol(x + 2, y + 1, step + 1);
            patrol(x + 1, y + 2, step + 1);
            patrol(x - 1, y + 2, step + 1);
            patrol(x - 2, y + 1, step + 1);
            board[x][y] = 0;
//            board[x][y] = x * size + y + 1;
//            solution.remove(solution.size() - 1);
        }
    }

    public static void main(String[] args) {
        System.out.println("请输入出发坐标：x=?, y=?");
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt(), y = scanner.nextInt();
        scanner.close();
        patrol(x, y, 1);
    }
}
