package branchbounding;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 八数码问题-分支限界法
 *
 * @author xieziwei99
 * 2019-11-18
 */
public class EightPuzzle {
    /**
     * 目标棋盘，取值0代表空
     */
    private static int[][] correctBoard = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}};
    private static List<Pt> pt = new LinkedList<>();

    /**
     * 评估函数，获得当前棋盘与正确棋盘的位置不匹配的数字个数
     */
    private static int getPrice(int[][] board, int[][] destBoard) {
        int price = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != 0 && board[i][j] != destBoard[i][j]) {
                    price++;
                }
            }
        }
        return price;
    }

    private static boolean boardNotInPath(int[][] board, List<int[][]> path) {
        for (int[][] b : path) {
            if (getPrice(board, b) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 把棋盘可以移动的所有情况列举并加入pt表中
     * @param path 棋盘的走子过程
     */
    private static void addToPt(List<int[][]> path) {
        int[][] currentBoard = path.get(path.size()-1);
        // 找到数字0的下标
        int x0 = 0, y0 = 0;
        first:
        for (int i = 0; i < currentBoard.length; i++) {
            for (int j = 0; j < currentBoard[i].length; j++) {
                if (currentBoard[i][j] == 0) {
                    x0 = i; y0 = j;
                    break first;
                }
            }
        }
        if (x0 - 1 >= 0) {
            int[][] tempBoard = new int[currentBoard.length][currentBoard.length];
            for (int i = 0; i < currentBoard.length; i++) {
                tempBoard[i] = currentBoard[i].clone();
            }
            tempBoard[x0][y0] = tempBoard[x0-1][y0];
            tempBoard[x0-1][y0] = 0;
            if (boardNotInPath(tempBoard, path)) {
                List<int[][]> newPath = new ArrayList<>(path);
                newPath.add(tempBoard);
                pt.add(new Pt(getPrice(tempBoard, correctBoard), newPath));
            }
        }
        if (x0 + 1 < currentBoard.length) {
            int[][] tempBoard = new int[currentBoard.length][currentBoard.length];
            for (int i = 0; i < currentBoard.length; i++) {
                tempBoard[i] = currentBoard[i].clone();
            }
            tempBoard[x0][y0] = tempBoard[x0+1][y0];
            tempBoard[x0+1][y0] = 0;
            if (boardNotInPath(tempBoard, path)) {
                List<int[][]> newPath = new ArrayList<>(path);
                newPath.add(tempBoard);
                pt.add(new Pt(getPrice(tempBoard, correctBoard), newPath));
            }
        }
        if (y0 - 1 >= 0) {
            int[][] tempBoard = new int[currentBoard.length][currentBoard.length];
            for (int i = 0; i < currentBoard.length; i++) {
                tempBoard[i] = currentBoard[i].clone();
            }
            tempBoard[x0][y0] = tempBoard[x0][y0-1];
            tempBoard[x0][y0-1] = 0;
            if (boardNotInPath(tempBoard, path)) {
                List<int[][]> newPath = new ArrayList<>(path);
                newPath.add(tempBoard);
                pt.add(new Pt(getPrice(tempBoard, correctBoard), newPath));
            }
        }
        if (y0 + 1 < currentBoard.length) {
            int[][] tempBoard = new int[currentBoard.length][currentBoard.length];
            for (int i = 0; i < currentBoard.length; i++) {
                tempBoard[i] = currentBoard[i].clone();
            }
            tempBoard[x0][y0] = tempBoard[x0][y0+1];
            tempBoard[x0][y0+1] = 0;
            if (boardNotInPath(tempBoard, path)) {
                List<int[][]> newPath = new ArrayList<>(path);
                newPath.add(tempBoard);
                pt.add(new Pt(getPrice(tempBoard, correctBoard), newPath));
            }
        }
    }

    private static void printBoard(int[][] board) {
        for (int[] row : board) {
            for (int col : row) {
                if (col == 0) {
                    System.out.print("  ");
                } else {
                    System.out.print(col + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        int[][] initBoard = {{1, 0, 3}, {4, 2, 6}, {7, 5, 8}};
        int[][] initBoard = {{0, 3, 5}, {1, 7, 2}, {8, 4, 6}};
        // 棋盘的走子过程
        List<int[][]> path = new ArrayList<>();
        path.add(initBoard);
        pt.add(new Pt(getPrice(initBoard, correctBoard), path));
        while (Collections.min(pt).price != 0) {
            path = Collections.min(pt).boards;
            pt.remove(Collections.min(pt));

            addToPt(path);
        }
        List<int[][]> solution = Collections.min(pt).boards;
        for (int i = 0; i < solution.size(); i++) {
            System.out.println("第" + i + "步：");
            printBoard(solution.get(i));
        }
    }
}


class Pt implements Comparable{
    /**
     * 当前棋盘的评估价值：取位置不正确的数字个数
     */
    int price;
    /**
     * 保存棋盘的走子过程
     */
    List<int[][]> boards;

    public Pt(int price, List<int[][]> boards) {
        this.price = price;
        this.boards = boards;
    }

    @Override
    public int compareTo(Object o) {
        return this.price - ((Pt)o).price;
    }
}