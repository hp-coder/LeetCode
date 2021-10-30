package data_structure;

import support.TreeNode;

import java.util.*;

public class LeetCode2021_10_29 {
    /*
     * 前序遍历： 根左右
     * 中序遍历： 左根右
     * 后序遍历： 左右中
     *
     * 递归/迭代利用栈或队列
     */

    /**
     * 二叉树前序遍历：
     * 递归方式
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) return list;
        list.add(root.val);
        list.addAll(preorderTraversal(root.left));
        list.addAll(preorderTraversal(root.right));
        return list;
    }

    /**
     * 二叉树前序遍历：
     * 非递归，栈方式：
     * 输出左子树，压栈左子树，往下切换左子树
     * 如果遍历完左子树，切换到右子树遍历
     * 沿左子树深度优先
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                res.add(root.val);
                stack.add(root);
                root = root.left;
            }
            root = stack.pop().right;
        }
        return res;
    }

    /**
     * 中序遍历二叉树：
     * 递归方式 左根右
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        list.addAll(inorderTraversal(root.left));
        list.add(root.val);
        list.addAll(inorderTraversal(root.right));
        return list;
    }

    /**
     * 中序遍历二叉树：
     * 非递归，栈方式：
     * 沿左子树往下搜索压栈，当叶子为空时搜索到底
     * 弹栈，弹出的值添加list，
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode pop = stack.pop();
            list.add(pop.val);
            root = pop.right;
        }
        return list;
    }

    /**
     * 后序遍历二叉树：
     * 递归方式：
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null) {
            return list;
        }
        list.addAll(postorderTraversal(root.left));
        list.addAll(postorderTraversal(root.right));
        list.add(root.val);
        return list;
    }

    /**
     * 后序遍历二叉树：
     * 非递归 栈方式：
     * 沿着左子树搜索，压栈，当搜索到叶子节点，开始弹栈
     * 弹出后判断是否存在右子树，如果不存在，则记录该节点，
     * 若存在，将节点压栈，节点转移至右子树搜索
     * 当遍历完右子树后，返回根节点，此时根节点存在右子树，需要利用一个标记值记录最近右子树的值 prev = root; root = null;
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                list.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return list;
    }


    /**
     * 层序遍历二叉树：
     * 队列方式：
     * 1、根节点入队，遍历至队列为空
     * 2、左子节点入队，右子节点入队， 遍历队列至空
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> lists = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int count = queue.size();
            ArrayList<Integer> sub = new ArrayList<>();
            while (count > 0) {
                TreeNode poll = queue.poll();
                sub.add(poll.val);
                if (poll.left != null) queue.add(poll.left);
                if (poll.right != null) queue.add(poll.right);
                count--;
            }
            lists.add(sub);
        }
        return lists;
    }

    /**
     * 二叉树最大深度：
     * dfs方式: 递归
     * r = 右子树最大深度
     * l = 左子树最大深度
     * depth = Max(r,l) + 1;
     *
     * @param root
     * @return
     */
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int r = maxDepth(root.right);
        int l = maxDepth(root.left);
        return Math.max(r, l) + 1;
    }

    /**
     * 二叉树最大深度：
     * <p>
     * 广度优先：基本和层序遍历一样， 记录装载了多少层节点也即深度
     *
     * @param root
     * @return
     */
    public int maxDepth2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int layer = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode poll = queue.poll();
                if (poll.left != null) queue.offer(poll.left);
                if (poll.right != null) queue.offer(poll.right);
                size--;
            }
            layer++;
        }
        return layer;
    }

    /**
     * 判断对称二叉树：
     * 递归方式
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    public boolean check(TreeNode l, TreeNode r) {
        if (l == null && r == null) return true;
        if (l == null || r == null) return false;
        return l.val == r.val && check(l.left, r.right) && check(l.right, r.left);
    }

    /**
     * 镜像二叉树：
     * 迭代方式：
     * 利用队列，初始化时入队根节点两次， 然后每次弹出两个元素比较，
     * 如果节点判断通过，则对应镜像入队左右节点的子节点
     *
     * @param root
     * @return
     */
    public boolean isSymmetric2(TreeNode root) {
        return check2(root, root);
    }

    private boolean check2(TreeNode l, TreeNode r) {
        Queue<TreeNode>queue = new LinkedList<>();
        queue.offer(l);
        queue.offer(r);
        while (!queue.isEmpty()){
            l = queue.poll();
            r = queue.poll();
            if (l==null && r == null) continue;
            if ((l==null || r==null ) || (l.val != r.val)) return false;
            queue.offer(l.left);
            queue.offer(r.right);

            queue.offer(l.right);
            queue.offer(r.left);
        }
        return true;
    }

    /**
     * 翻转二叉树：
     * 递归思想：到叶子节点后，其实就是翻转两个子节点
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root){
        if (root == null)return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }


}
