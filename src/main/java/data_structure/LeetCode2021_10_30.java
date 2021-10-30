package data_structure;

import support.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode2021_10_30 {


    /**
     * 路经总和：
     * 根节点到叶子节点的路径里 每个以前节点值之和等于targetSum
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> queVal = new LinkedList<>();
        queue.offer(root);
        queVal.offer(root.val);
        while (!queue.isEmpty()) {
            TreeNode now = queue.poll();
            int temp = queVal.poll();
            if (now.left == null && now.right == null) {
                if (temp == targetSum) {
                    return true;
                }
                continue;
            }
            if (now.left != null) {
                queue.offer(now.left);
                queVal.offer(now.left.val + temp);
            }
            if (now.right != null) {
                queue.offer(now.right);
                queVal.offer(now.right.val + temp);
            }
        }
        return false;
    }

    /**
     * 二叉树中从根节点到叶子节点是否存在路径和为：targetSum
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum2(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        return hasPathSum2(root.left, targetSum - root.val) || hasPathSum2(root.right, targetSum - root.val);
    }

    /**
     * 二叉搜索树：
     * 每个节点都有如下特性：
     * 大于左子树上任意一个节点的值
     * 小于右子树上任意一个节点的值
     * <p>
     * 迭代方式
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode searchBST(TreeNode root, int val) {
//        if (root == null || root.val == val) return root;
//        while (root !=null && val != root.val){
//            root = val < root.val ? root.left : root.right;
//        }
//        return root;

        if (root == null || root.val == val) return root;
        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);

    }

    /**
     * BTS插入值保持BTS结构：
     * BTS：
     * 对于任意节点root而言，左子树（若存在）上所有节点都小于root.val,
     * 右子树（若存在）上所有节点都大于root.val， 且它们都是二叉搜索树
     *
     * 该方式是寻找新的叶子节点的位置
     *
     * @param root
     * @param val
     * @return
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        TreeNode pos = root;
        while (pos != null) {
            if (val < pos.val) {
                if (pos.left == null) {
                    pos.left = new TreeNode(val);
                    break;
                } else {
                    pos = pos.left;
                }
            }else{
                if (pos.right == null){
                    pos.right = new TreeNode(val);
                    break;
                }else{
                    pos = pos.right;
                }
            }
        }
        return root;
    }
}