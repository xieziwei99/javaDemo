package backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Stack;

/**
 * 迷宫问题-回溯法
 *
 * @author xieziwei99
 * 2019-11-08
 */
public class MazeProblem {
    private static char[][] maze = new char[10][10];
    private static P in = new P(1, 1), out = new P(8, 8);
    private static Stack<P> stack = new Stack<>();
    // 从文件中读入迷宫
    static  {
        try (BufferedReader reader = new BufferedReader(new FileReader("maze.txt"))) {
            int i = 0;
            String line = reader.readLine();
            while (line != null) {
                maze[i] = line.replaceAll(" ", "").toCharArray();
                line = reader.readLine();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean isOk(int x, int y) {     // 判断迷宫中点x, y是否可走
        return (x >= 0 && x <= 9) && (y >= 0 && y <= 9) && '□' == maze[x][y];
    }

    public static void solution() {
        stack.push(in);
        while (!stack.empty()) {
            P p = stack.peek();
            if (p.equals(out)) {     // 到达出口
                maze[p.x][p.y] = '√';
                return;
            }
            // 向右走
            if (isOk(p.x, p.y + 1)) {
                maze[p.x][p.y] = '→';
                stack.push(new P(p.x, p.y + 1));
                continue;
            }
            // 向下走
            if (isOk(p.x + 1, p.y)) {
                maze[p.x][p.y] = '↓';
                stack.push(new P(p.x + 1, p.y));
                continue;
            }
            // 向左走
            if (isOk(p.x, p.y - 1)) {
                maze[p.x][p.y] = '←';
                stack.push(new P(p.x, p.y - 1));
                continue;
            }
            // 向上走
            if (isOk(p.x - 1, p.y)) {
                maze[p.x][p.y] = '↑';
                stack.push(new P(p.x - 1, p.y));
                continue;
            }
            maze[p.x][p.y] = '×';
            stack.pop();
        }
    }

    public static void main(String[] args) {
        solution();
        for (char[] chars : maze) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
        System.out.println(stack.toString());
    }
}

// 点类
class P {
    int x; int y;

    public P() {
        this(0, 0);
    }

    public P(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        P p = (P) o;
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "P{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}