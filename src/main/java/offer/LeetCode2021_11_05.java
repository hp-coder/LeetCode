package offer;

import com.sun.jdi.IntegerType;
import support.TreeNode;

import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LeetCode2021_11_05 {

    /**
     * 从上至下打印二叉树：
     * 时间复杂度：遍历n个节点 也即 O(n)
     * 空间复杂度：最坏为平衡二叉树，最多有n/2个节点在queue中，也即 O(n)
     * @param root
     * @return
     */
    public int[] levelOrder(TreeNode root){
        if (root == null) return new int[0];
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<Integer> list = new ArrayList<>();
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (poll == null){
                continue;
            }
            list.add(poll.val);
            queue.offer(poll.left);
            queue.offer(poll.right);
        }
        int[] arr = new int[list.size()];
        int a = 0;
        for (Integer nums : list) {
            arr[a++] = nums;
        }
        return arr;
    }

    /**
     * 返回二维集合
     * @return
     */
    public List<List<Integer>> levelOrder2(TreeNode root){
        if(root == null){
            return Collections.emptyList();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty()){
            List<Integer> sub = new ArrayList<>();
            for (int i = queue.size(); i>0; i--){
                root = queue.poll();
                sub.add(root.val);
                if (root.left !=null){
                    queue.offer(root.left);
                }
                if (root.right!=null){
                    queue.offer(root.right);
                }
            }
            ans.add(sub);
        }
        return ans;
    }

    public List<List<Integer>> levelOrder3(TreeNode root){
        if(root == null){
            return Collections.emptyList();
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<List<Integer>> ans = new ArrayList<>();
        while (!queue.isEmpty()){
            LinkedList<Integer> sub = new LinkedList<>();
            for (int i = queue.size(); i>0; i--){
                root = queue.poll();
                if (ans.size() % 2 == 0){
                    sub.addLast(root.val);
                }else {
                    sub.addFirst(root.val);
                }
                if (root.left !=null){
                    queue.offer(root.left);
                }
                if (root.right!=null){
                    queue.offer(root.right);
                }
            }
            ans.add(sub);
        }
        return ans;
    }
}
