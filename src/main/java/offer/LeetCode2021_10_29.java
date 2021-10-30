package offer;

/**
 * 剑指offer
 */
public class LeetCode2021_10_29 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 倒序打印链表：
     * 记录链表长度
     * 倒序遍历数组，顺序添加元素
     *
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        ListNode node = head;
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        int[] nums = new int[count];
        node = head;
        for (int i = count - 1; i >= 0; --i) {
            nums[i] = node.val;
            node = node.next;
        }
        return nums;
    }


}
