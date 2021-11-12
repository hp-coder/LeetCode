package data_structure;

import support.ListNode;

public class LeetCode2021_10_25 {
    /**
     * 查找插入元素的位置：
     * 二分查找法： 要点 mid = (high-low)/2+low;
     * 查找到最接近或者相等的元素时坐标+1
     *
     * @param nums
     * @param target
     * @return
     */
    public int searchInsert(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] < target) {
                low = mid + 1;
            } else if (nums[mid] > target) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return high + 1;
    }

    /**
     * 查找第一个错误的版本：
     * isBadVersion判断版本是否为错误版本
     * 二分查找： 如果中点为错误版本，则缩小上界
     *
     * @param n
     * @return
     */
    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (isBadVersion(mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    private boolean isBadVersion(int mid) {
        return false;
    }

    /**
     * 重塑矩阵：
     * 获取原始矩阵的长宽，判断新矩阵容量
     * 创建新矩阵 int[r][c]
     * 要点：ans[i/c][i%c] = mat[i/n][i%n];
     *
     * @param mat
     * @param r
     * @param c
     * @return
     */
    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int m = mat.length;
        int n = mat[0].length;
        if (m * n != r * c) {
            return mat;
        }
        int[][] ans = new int[r][c];
        for (int i = 0; i < m * n; i++) {
            ans[i / c][i % c] = mat[i / n][i % n];
        }
        return ans;
    }

    /**
     * 第一个不重复的字符：
     * 当前字符索引 == 当前字符最后一个索引位置
     *
     * @param s
     * @return
     */
    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        if (chars.length == 0) {
            return -1;
        }
        if (chars.length == 1) {
            return 0;
        }
        for (int i = 0; i < chars.length; i++) {
            if (s.indexOf(String.valueOf(chars[i])) == s.lastIndexOf(String.valueOf(chars[i]))) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 是否能通过magazine中的字符构建ransomNote：
     * 使用stringbuilder删除子序中的字符 如果不存在则无法构建
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct1(String ransomNote, String magazine) {
        StringBuilder sb = new StringBuilder(magazine);
        int index;
        for (char c : ransomNote.toCharArray()) {
            index = sb.indexOf(String.valueOf(c));
            if (index != -1) {
                sb.deleteCharAt(index);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * 对每个字符-'a'得到 1-26的索引，
     * 分别计算两个字符串中每个字符出现的次数，
     * 比较子序中每个字符都必须小于等于父序列中的字符数量
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean canConstruct2(String ransomNote, String magazine) {
        int[] a = new int[26];
        int[] b = new int[26];
        for (char c : ransomNote.toCharArray()) {
            a[c - 'a'] += 1;
        }
        for (char c : magazine.toCharArray()) {
            b[c - 'a'] += 1;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 是否包含子串：
     * 分别对两个字符串中的字符出现数量，
     * 存储计算ascii码保存，
     * 然后对应索引位置的数量对比即可
     *
     * @param ransomNote
     * @param magazine
     * @return
     */
    public boolean isAnagram(String ransomNote, String magazine) {
        int[] a = new int[26];
        int[] b = new int[26];
        for (char c : ransomNote.toCharArray()) {
            a[c - 'a'] += 1;
        }
        for (char c : magazine.toCharArray()) {
            b[c - 'a'] += 1;
        }
        for (int i = 0; i < a.length; i++) {
            if (a[i] != b[i]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 寻找中间节点：
     * 快慢指针，当快指针步长为慢指针当两倍时，快指针走到空或者下一节点为空时，慢指针刚好走过一半的路程
     *
     * @param head
     * @return
     */
    public ListNode middleNode(ListNode head) {
        ListNode s = head, f = head;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    /**
     * 删除倒数第n节点：
     * 快慢指针，快指针先走n步，然后快慢指针一起走，
     * 当快指针下一个节点==null时，将慢指针下一个节点就是被删除节点，
     * next指向被删除节点的下一个节点即可
     *
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode q = head, p = head;
        for (int i = 0; i < n; i++) {
            if (q.next != null) {
                q = q.next;
            } else {
                return head.next;
            }
        }
        while (q.next != null) {
            q = q.next;
            p = p.next;
        }
        p.next = p.next.next;
        return head;
    }

    /**
     * 有效的数独：
     * 在横向纵向和3*3的矩阵中不能重复
     * 将3*3矩阵看作单个元素
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        boolean[][] row = new boolean[9][9];
        boolean[][] col = new boolean[9][9];
        boolean[][] box = new boolean[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '1';
                    int boxIndex = i / 3 * 3 + j / 3;
                    if (row[i][num] || col[j][num] || box[boxIndex][num]) {
                        return false;
                    } else {
                        row[i][num] = true;
                        col[j][num] = true;
                        box[boxIndex][num] = true;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode2021_10_25 code = new LeetCode2021_10_25();
        System.out.println("code.firstUniqChar(\"leetCode\") = " + code.firstUniqChar("aabb"));
    }
}
