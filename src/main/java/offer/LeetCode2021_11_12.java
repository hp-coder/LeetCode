package offer;

import data_structure.LeetCode2021_10_25;
import data_structure.LeetCode2021_10_26;
import support.ListNode;

import javax.swing.*;
import javax.tools.ForwardingFileObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.NavigableMap;
import java.util.Set;
import java.util.concurrent.CyclicBarrier;

public class LeetCode2021_11_12 {

    /**
     * 股票的最大利润
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices == null) return 0;
        int cost = Integer.MAX_VALUE, profit = 0;
        for (int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, profit - cost);
        }
        return profit;
    }

    /**
     * 最大子序和：
     * time: O(n)
     * space: O(1)
     * <p>
     * 对于结果 dp[i]来说
     * dp[i] = x(之前的子序和) + nums[i]
     * 从而可知： 为了使dp[i]最大，则要么 x > 0 ， 要么就是 nums[i]本身
     *
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    /**
     * 礼物的最大价值，
     * 从矩阵左上角开始，只能向右或者向下搜索
     * <p>
     * 动态规划：
     * 可以看作 对于某一点 grid(i，j)， 其被访问的方式只能通过 grid(i-1) 或 grid(i,j-1); 也即上方和左侧点访问
     * 对于路径和最大也即 当前节点 + 访问该节点的前一个节点中的最大值
     * 设路线最大值为 f(i,j) 那么 就有 f(i,j) =  max[f(i,j-1),f(i-1,j)] + grid(i,j);
     * 转移方程：
     * 当i = 0  && j= 0  为起始元素
     * 当i=0 && j != 0 为矩阵第一行元素， 只能从左侧到达
     * 当i！=0 && j = 0 为矩阵的第一列元素， 只能从上方到达
     * 当 i ！= 0 && j！=0 可以从左侧或者上方到达
     * <p>
     * dp(i,j) = {
     * grid(i,j);
     * grid(i,j)+ dp(i,j-1);
     * grid(i,j)+ dp(i-1,j);
     * grid(i,j) + max[dp(i,j-1),dp(i-1,j);
     * }
     * <p>
     * dp问题，也常用扩大空间以处理边界问题，也即 当 i，j =0时的特殊处理
     */
    public int maxValue(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) continue;
                if (i == 0) grid[i][j] += grid[i][j - 1];
                else if (j == 0) grid[i][j] += grid[i - 1][j];
                else grid[i][j] += Math.max(grid[i - 1][j], grid[i][j - 1]);
            }
        }
        return grid[m - 1][n - 1];
    }

    /**
     * 多使用一行一列的方式，不需要再次处理边界问题，统一为 i，j != 0的情况
     *
     * @param grid
     * @return
     */
    public int maxValueWithExtraSpace(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }


    /**
     * 把数字翻译成字符串
     * 每一位数字都可以翻译，但两个数字连在一起是否可以翻译，
     * 要判断是否在 [10,25] 区间 因为00 01，26，27是无法对应ascii吗但小写字母
     * <p>
     * f(i) = f(i-1) + f(i-2) [i-1 >= 0,  10<= x <= 25]
     * 由于 f(i) 只 跟 f(i-1) f(i-2)相关，所以可以利用滚动数组来优化空间
     * <p>
     * 类似于青蛙跳台阶的问题，但是能否跳两级台阶需要满足区间
     */
    public int translateNum(int num) {
        String src = String.valueOf(num);
        int a = 1, b = 1;
        for (int i = 2; i <= src.length(); i++) {
            String temp = src.substring(i - 2, i);
            int c = temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0 ? a + b : a;
            // a = i  b= i-1的位置
            b = a;
            a = c;
        }
        return a;
    }

    /**
     * 最长不重复子串长度
     * 滑动窗口：
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubString(String s) {
        char[] cs = s.toCharArray();
        HashMap<Character, Integer> map = new HashMap<>();
        int ans = 0, start = -1;
        for (int i = 0; i < cs.length; i++) {
            if (map.containsKey(cs[i])) {
                start = Math.max(start, map.getOrDefault(cs[i], -1));
            }
            map.put(cs[i], i);
            ans = Math.max(ans, i - start);
        }
        return ans;
    }

    /**
     * 删除链表中元素：
     * 删除头节点要特殊处理 return head.next;
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode deleteNode(ListNode head, int val) {
        if (head.val == val) return head.next;
        ListNode pre = head, cur = head.next;
        while (cur != null && cur.val != val) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) pre.next = cur.next;
        return head;
    }

    /**
     * 返回倒数第k个元素
     * 快慢指针
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode a = head, b = head;
        for (int i = 0; i < k; i++) {
            if (b == null) return null;
            b = b.next;
        }
        while (b != null) {
            b = b.next;
            a = a.next;
        }
        return a;
    }

    /**
     * 合并链表：
     * time: O(m + n)
     * space: O(1)
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTowLists(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0), cur = dum;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }

    /**
     * 链表的第一个公共节点
     * 双指针：
     * 对于公共节点之前可以定义为长度为 a ,b 对于公共节点后的长度 定义为 c
     * 故 A指针 走过路径 a + c + (到头后指向另一个链表头部) b
     * B指针 走过 b + c (到头指向头部) a
     * 因为同时出发且走过的路径一样，必然走到公共节点
     * <p>
     * 第二种空间换时间
     * 使用set集合 当add返回false时已经存在该公共节点
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA, node2 = headB;
        while (node1 != node2) {
            node1 = node1 != null ? node1.next : headB;
            node2 = node2 != null ? node2.next : headA;
        }
        return node1;
    }

    /**
     * 交换奇偶数
     *
     * @param nums
     * @return
     */
    public int[] exchange(int[] nums) {
        int i = 0, j = nums.length - 1, temp;
        while (i < j) {
            while (i < j && (nums[i] & 1) == 1) i++;
            while (i < j && (nums[j] & 1) == 0) j--;
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
        return nums;
    }

    public int[] exchange2(int[] nums) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if ((nums[fast] & 1) == 1) {
                swap(nums, fast, slow++);
            }
            fast++;
        }
        return nums;
    }

    private void swap(int[] nums, int fast, int slow) {
        int temp = nums[fast];
        nums[fast] = nums[slow];
        nums[slow] = temp;
    }
}
