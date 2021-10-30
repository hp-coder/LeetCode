package offer;

import javax.swing.*;
import java.util.HashSet;
import java.util.Stack;
import java.util.WeakHashMap;

/**
 * 剑指offer
 */
public class LeetCode20211028 {
    /**
     * 栈模拟队列：
     */
    static class CQueue {
        Stack<Integer> in;
        Stack<Integer> out;

        public CQueue() {
            this.in = new Stack<>();
            this.out = new Stack<>();
        }

        public void appendTail(int value) {
            in.push(value);
        }

        public int deleteHead() {
            if (out.isEmpty() && in.isEmpty()) {
                return -1;
            }
            if (in.isEmpty()) {
                while (!out.isEmpty()) {
                    in.push(out.pop());
                }
            }
            return in.pop();
        }
    }

    /**
     * 斐波那契数列：
     * 纯使用递归会超时
     * 0 1 1 2 3 5 8
     * b a
     * -----------------
     * a = a + b;
     * b = a - b;
     * 就完成了一次迭代
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0 || n == 1) return n;
        int a = 1, b = 0;
        for (int i = 1; i < n; i++) {
            a = a + b;
            b = a - b;
            a %= 1000000007;
        }
        return a;
    }

    /**
     * 找出任意重复数字
     * 长度为n  所有数字都在 0，n-1范围内
     *
     * @param nums
     * @return
     */
    public int findRepeatNumber(int[] nums) {
        int[] arr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[nums[i]]++;
            if (arr[nums[i]] > 1) {
                return nums[i];
            }
        }
        return -1;
    }

    /**
     * 二维数组中查找元素：
     * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，
     * 每一列都按照从上到下递增的顺序排序。请完成一个高效的函数，
     * 输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     * <p>
     * 要点： 从右上角开始遍历
     * 如果  当前数 < target  row ++
     * 当前数 > target col --
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (matrix[row][col] > target) {
                col--;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 走台阶 一步 两步 有多少种走法的问题
     * 走终点n的情况就走一步 或者走两步， 也就是 f(n) = f(n-1) + f(n-2)的方式 斐波那契数列
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n <= 1) return 1;
        int a = 0, b = 1;
        for (int i = 0; i < n; i++) {
            b = a + b;
            a = b - a;
            b %= 1000000007;
        }
        return b;
    }

    /**
     * 矩阵中的路径： 递归 + dfs + 剪枝
     * 给定一个m x n 二维字符网格board 和一个字符串单词word 。如果word 存在于网格中，返回 true ；否则，返回 false 。
     * <p>
     * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，
     * 其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
     * 同一个单元格内的字母不允许被重复使用。
     * <p>
     * 深度优先搜索 + 回溯（剪枝）：
     * 如果该节点被遍历过 或者 当前节点不对应当前word中遍历到的元素时回溯，返回false
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        char[] letters = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, letters, i, j, 0)) return true;
            }
        }
        return false;
    }

    /**
     * 深度优先搜索
     *
     * @param board
     * @param letters
     * @param i
     * @param j
     * @param k
     * @return
     */
    private boolean dfs(char[][] board, char[] letters, int i, int j, int k) {
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != letters[k]) {
            return false;
        }
        if (k == letters.length - 1) {
            return true;
        }
        //至0或置空后，搜索时就可以自动排除
        board[i][j] = '\0';
        //朝四个方向尝试前进一步
        boolean res = dfs(board, letters, i + 1, j, k + 1) ||
                dfs(board, letters, i - 1, j, k + 1) ||
                dfs(board, letters, i, j + 1, k + 1) ||
                dfs(board, letters, i, j - 1, k + 1);
        board[i][j] = letters[k];
        return res;
    }


    /**
     * 替换空格：
     * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"
     * 不使用string的replace函数的情况
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        if(s == null){
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<s.length(); i++){
            if(" ".equals(String.valueOf(s.charAt(i)))){
                sb.append("%20");
            }else{
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
