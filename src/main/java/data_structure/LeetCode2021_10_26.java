package data_structure;

import support.ListNode;

import java.util.HashMap;
import java.util.Stack;

public class LeetCode2021_10_26 {
    /**
     * 旋转数组（给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。）：
     * 整体翻转数组为倒序后
     * 再次分别根据k翻转两个部分
     *
     * @param nums
     * @param k
     */
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    /**
     * 旋转数组
     * 两界双指针方式翻转数组
     *
     * @param nums
     * @param s
     * @param e
     */
    private void reverse(int[] nums, int s, int e) {
        while (s < e) {
            int temp = nums[s];
            nums[s++] = nums[e];
            nums[e--] = temp;
        }
    }

    /**
     * 有序数组的平方后排序（存在负数）：
     * 两界双指针方式：绝对值大的计算平方倒序放入新数组，移动指针
     *
     * @param nums
     * @return
     */
    public int[] sortedSquares(int[] nums) {
        int[] arr = new int[nums.length];
        int low = 0;
        int end = nums.length - 1;
        int high = end;
        while (low <= high) {
            int absLow = Math.abs(nums[low]);
            int absHigh = Math.abs(nums[high]);
            int num;
            if (absHigh > absLow) {
                num = nums[high--];
            } else {
                num = nums[low++];
            }
            arr[end--] = num * num;
        }
        return arr;
    }

    /**
     * 最长无重复元素的字序列
     * TODO 滑动窗口的方式 还需多理解
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        for (int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        int n = s.length();
        int res = 0;
        int start = 0; // 窗口开始位置
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }
        return res;
    }

    /**
     * 有效的括号：
     * 遍历到左侧括号时，入栈对应到右侧括号，
     * 遍历到右侧括号时，栈是否为空||弹出元素是否和右侧括号相同即可，
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || c != stack.pop()) return false;
        }
        return stack.isEmpty();
    }

    /**
     * 合并两个链表：
     * 保留dummyHead的虚拟头部；
     * 如果两个链表都不为空时，遍历比较大小后分别拼接到新链表后面
     * 当任意一个链表遍历至空时，直接拼接另一链表至结尾即可
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode cur = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        if (l1 == null) {
            cur.next = l2;
        } else {
            cur.next = l1;
        }
        return dummyHead.next;
    }

    /**
     * 环形链表：
     * 当存在环时，快慢指针一定回相遇；
     * 要点： 快指针走两步，慢指针走一步
     *
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    /**
     * 翻转链表:
     * 标记前一个节点：pre
     * 标记当前节点：cur
     * 翻转就是将当前节点的下一个节点指向前一个节点
     * 然后当前节点对于下一个需要翻转的节点就是前一个节点， 故 pre = cur；
     * 将cur置为下一个需要翻转的节点
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null, cur = head;
        while (cur != null) {
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    /**
     * 删除链表中的重复元素：
     * 当遍历未完成时，如果当前节点和下一节点的值相同，
     * 则将当前节点的下一节点指向后两个节点
     * 要点：保留指向头部的引用，跨过重复值
     *
     * @param head
     * @return
     */
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = head;
        while (head != null && head.next != null) {
            if (head.val != head.next.val) {
                head = head.next;
            } else {
                head.next = head.next.next;
            }
        }
        return dummyHead;
    }

    /**
     * 移除链表中指定元素：
     * 要点：头节点如果也是被删除元素则需要引入虚拟节点指向头，从头开始遍历
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        ListNode header = new ListNode(-1);
        ListNode cur = header;
        header.next = head;
        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return header.next;
    }

    /**
     * 使用两个栈结构模拟队列：
     * 要点：一个栈做push保存，需要pop/peek时将数据转移至另一个栈中；
     * 如果out栈非空就一直操作至空，
     * 如果out栈为空则尝试将in栈中数据转移至out中操作
     */
    static class MyQueue {
        Stack<Integer> in;
        Stack<Integer> out;

        public MyQueue() {
            this.in = new Stack<>();
            this.out = new Stack<>();
        }

        public void push(int x) {
            in.push(x);
        }

        public int pop() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
            return out.pop();
        }

        public int peek() {
            if (out.isEmpty()) {
                while (!in.isEmpty()) {
                    out.push(in.pop());
                }
            }
            return out.peek();
        }

        public boolean empty() {
            return in.isEmpty() && out.isEmpty();
        }
    }

    /**
     * 字符串排列：
     * 子序列是否在s2中 顺序不需要控制
     * 使用数组方式的滑动窗口
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion(String s1, String s2) {
        char[] pattern = s1.toCharArray();
        char[] text = s2.toCharArray();
        int pLen = pattern.length;
        int tLen = text.length;
        int[] pFreq = new int[26];
        int[] tFreq = new int[26];
        for (char c : pattern) {
            pFreq[c - 'a']++;
        }
        int pCount = 0;
        for (int i = 0; i < 26; i++) {
            if (pFreq[i] > 0) {
                pCount++;
            }
        }
        int right = 0, left = 0;
        //当窗口中某字符的个数与s1中的对应相等时才计数
        int winCount = 0;
        while (right < tLen) {
            if (pFreq[text[right] - 'a'] > 0) {
                tFreq[text[right] - 'a']++;
                if (tFreq[text[right] - 'a'] == pFreq[text[right] - 'a']) {
                    winCount++;
                }
            }
            right++;
            while (pCount == winCount) {
                if (right - left == pLen) {
                    return true;
                }
                if (pFreq[text[left] - 'a'] > 0) {
                    tFreq[text[left] - 'a']--;
                    if (tFreq[text[left] - 'a'] < pFreq[text[left] - 'a']) {
                        winCount--;
                    }
                }
                left++;
            }
        }
        return false;
    }

    /**
     * 检查s1序列是否存在与s2中（忽视字符顺序）：
     * map集合方式，还是滑动窗口的解法：
     *
     * s2map固定窗口大小
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean checkInclusion2(String s1, String s2) {
        int lenS1 = s1.length();
        int lenS2 = s2.length();
        if (lenS1 > lenS2) return false;
        HashMap<Character, Integer> mapS1 = new HashMap<>();
        HashMap<Character, Integer> mapS2 = new HashMap<>();
        for (char c : s1.toCharArray()) {
            mapS1.put(c, mapS1.getOrDefault(c, 0) + 1);
        }
        int index = 0;
        for (int i = 0; i < lenS1; i++, index++) {
            char c = s2.charAt(i);
            mapS2.put(c, mapS2.getOrDefault(c, 0) + 1);
        }
        while (index < lenS2) {
            if (mapS2.equals(mapS1)) return true;
            char before = s2.charAt(index - lenS1);
            char after = s2.charAt(index);
            mapS2.put(before,mapS2.get(before)-1);
            if (mapS2.get(before) == 0) {
                mapS2.remove(before);
            }
            mapS2.put(after, mapS2.getOrDefault(after, 0) + 1);
            index++;
        }
        return mapS2.equals(mapS1);
    }

    public static void main(String[] args) {
        LeetCode2021_10_26 code = new LeetCode2021_10_26();
    }
}
