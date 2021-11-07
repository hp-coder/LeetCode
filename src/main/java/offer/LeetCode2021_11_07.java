package offer;

import support.TreeNode;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LeetCode2021_11_07 {

    /**
     * 镜像二叉树：
     * 递归方式
     * Time : O(n)
     * Space: O(n)
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTree(root.left);
        TreeNode right = mirrorTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    /**
     * 镜像二叉树：
     * 迭代方式：
     * 层序加 左右子数交换
     * time: O(n)
     * space: O(n)
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode dummyRoot = root;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            root = queue.poll();
            if (root.left != null) queue.offer(root.left);
            if (root.right != null) queue.offer(root.right);

            TreeNode temp = root.left;
            root.left = root.right;
            root.right = temp;
        }
        return dummyRoot;
    }

    /**
     * 判断二叉树是否是另一棵树的一部分
     * @param A
     * @param B
     * @return
     */
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        //特殊情况特殊处理
        if (A == null || B == null) {
            return false;
        }
        //首先判断B是否是以A根节点开始 ｜｜ 是否是左子树的一部分 ｜｜ 是否是右子树的一部分
        return isSub(A,B) || isSubStructure(A.left,B) || isSubStructure(A.right,B);
    }

    public boolean isSub(TreeNode A, TreeNode B) {
        //当B为空时越过了B当叶子节点，也即匹配完成
        if (B == null) {
            return true;
        }
        //当A为空时越过了A当叶子节点，即为为匹配，返回false，同时判断A不为空时，值是否相等
        if (A == null || A.val != B.val){
            return false;
        }
        //判断A的左节点和B的左右节点是否一样
        return isSub(A.left, B.left) && isSub(A.right, B.right);
    }

    /**
     * 对称二叉树：
     * 递归方式
     * time:O(n)
     * space:o(n)
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root){
        return root == null ? true : recur(root.left,root.right);
    }

    /**
     * 当左节点和有节点同时为空时，true
     * 当其中之一为空 另一个不为空， 或 值不想等时，false
     * 校验 L.left.val == R.right.val
     * @param left
     * @param right
     * @return
     */
    public boolean recur(TreeNode left, TreeNode right){
        if (left == null && right ==null)return true;
        if (left == null || right == null || left.val != right.val) return false;
        return recur(left.left,right.right) && recur(left.right,right.left);
    }

}
