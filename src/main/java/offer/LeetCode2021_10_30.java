package offer;

import support.TreeNode;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Stack;

public class LeetCode2021_10_30 {

    private Map<Integer, Integer> indexMap;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        indexMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right,
                                int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }
        //前序遍历中第一个元素就是根节点
        int preorder_root = preorder_left;
        //在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);
        //建立根节点
        TreeNode root = new TreeNode(preorder[preorder_root]);
        //得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        //递归的构造左子树，并连到根节点
        //前序遍历中，从左边界+1开始的【size_left_subtree】个元素就对应了中序遍历中【从左边界开始到root-1】的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree
                , inorder_left, inorder_root - 1);
        //递归构造右子树，
        //前序遍历中【从左边界+1+左子树节点数 开始到 右边界】的元素就对应了中序遍历中【从根+1 到 右边界】到元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preVal = preorder[i];
            TreeNode peek = stack.peek();
            if (peek.val != inorder[inorderIndex]){
                peek.left = new TreeNode(preVal);
                stack.push(peek.left);
            }else{
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]){
                    peek = stack.pop();
                    inorderIndex++;
                }
                peek.right = new TreeNode(preVal);
                stack.push(peek.right);
            }
        }
        return root;
    }

    /**
     * 模拟栈，各个方法调用O(1)
     */
    class MinStack {
        Stack<Integer> storage,min;
        /** initialize your data structure here. */
        public MinStack() {
            storage = new Stack<>();
            min = new Stack<>();
        }

        public void push(int x) {
            storage.push(x);
            if(min.isEmpty() || min.peek() >= x){
                min.push(x);
            }
        }

        public void pop() {
            if(storage.pop().equals(min.peek())){
                min.pop();
            }
        }

        public int top() {
            return storage.peek();
        }

        public int min() {
            return min.peek();
        }
    }
}