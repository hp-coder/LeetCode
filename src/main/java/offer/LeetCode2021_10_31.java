package offer;

import support.ListNode;
import support.Node;
import support.TreeNode;

import javax.management.relation.Role;
import java.lang.invoke.VarHandle;
import java.nio.channels.NonWritableChannelException;
import java.util.*;
import java.util.zip.CheckedInputStream;

public class LeetCode2021_10_31 {

    /**
     * 翻转链表：
     * 迭代方式：
     *
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    /**
     * 随机链表的拷贝：
     * 回溯+哈希表记录每个被遍历的节点的next和random是否创建在cache中，
     * 如果不存在则创建后保存，对于random/next指向的节点也如此递归
     */
    Map<Node, Node> cache = new HashMap<>();

    public Node copyRandomList(Node head) {
        if (head == null) return null;
        if (!cache.containsKey(head)) {
            Node headNew = new Node(head.val);
            cache.put(head, headNew);
            headNew.next = copyRandomList(head.next);
            headNew.random = copyRandomList(head.random);
        }
        return cache.get(head);
    }

    /**
     * 随机拷贝链表：
     * A->B->C
     * A-A'->B->B'->C->C'
     * 遍历第一次，增加 复制节点
     * 第二次，将复制节点的随机指针指向 原节点随机指针的下一个
     * 第三次，保留head.next，将复制节点的next 指向下一个复制节点，将复制节点的next指向 原节点的next的下一个
     * <p>
     * 时间复杂度O(n)：只需要遍历三次
     * 空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        if (head == null) return null;
        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
        }
        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = node.next;
            newNode.random = node.random != null ? node.random.next : null;
        }
        Node newHead = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node newNode = node.next;
            node.next = node.next.next;
            newNode.next = node.next != null ? newNode.next.next : null;
        }
        return newHead;
    }

    /**
     * 验证二叉搜索树：
     * 要点： 迭代中序遍历后节点值是升序
     * 或
     * 递归：左节点严格小于根节点， 右节点严格大于根节点
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode node, long min, long max) {
        if (node == null) return true;
        if (node.val <= min || node.val >= max) {
            return false;
        }
        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }

    /**
     * 检验二叉搜索树：中序 需要记录沿着左子树搜索的路径
     *
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        double order = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= order) {
                return false;
            }
            order = root.val;
            root = root.right;
        }
        return true;
    }

    /**
     * 两数之和：二叉搜索数版本
     * 递归版
     *
     * @param node
     * @param k
     * @return
     */
    public boolean sum(TreeNode node, int k) {
        return find(node, k, new HashSet<>());
    }

    public boolean find(TreeNode root, int k, Set<Integer> set) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return find(root.left, k, set) || find(root.right, k, set);
    }

    /**
     * 两数之和：层序遍历版
     *
     * @param node
     * @param k
     * @return
     */
    public boolean sum2(TreeNode node, int k) {
        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        while (!queue.isEmpty()) {
            if(queue.peek() == null){
                queue.remove();
            }else {
                TreeNode poll = queue.poll();
                if (set.contains(k - poll.val)) {
                    return true;
                }
                set.add(poll.val);
                queue.add(poll.left);
                queue.add(poll.right);
            }
        }
        return false;
    }

    public boolean sum3(TreeNode node, int k) {
        List<Integer> list = new ArrayList<>();
        inorder(node, list);
        int i = 0, j = list.size() - 1;
        while (i < j) {
            int sum = list.get(i) + list.get(j);
            if (sum > k) {
                j--;
            } else if (sum < k) {
                i++;
            } else {
                return true;
            }
        }
        return false;
    }

    public void inorder(TreeNode node, List<Integer> list) {
        if (node == null) return;
        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }


    /**
     * BST中，pq两节点的最近公共祖先节点
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null) return null;
        if(p == null && q == null) return root;
        TreeNode parent = root;
        while(true){
            if(p.val < parent.val && q.val < parent.val){
                parent = parent.left;
            }else if(p.val > parent.val && q.val > parent.val ){
                parent = parent.right;
            }else{
                break;
            }
        }
        return parent;
    }
}
