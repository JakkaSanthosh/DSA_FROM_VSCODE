import java.util.*;

class PostorderTraversal {
    // Recursive Postorder
    void postorderRecursive(TreeNode root) {
        if (root != null) {
            postorderRecursive(root.left);
            postorderRecursive(root.right);
            System.out.print(root.val + " ");
        }
    }

    // Iterative Postorder using Two Stacks
    void postorderIterative(TreeNode root) {
        if (root == null) return;

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);

            if (node.left != null) stack1.push(node.left);
            if (node.right != null) stack1.push(node.right);
        }

        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().val + " ");
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        PostorderTraversal obj = new PostorderTraversal();
        System.out.print("Recursive Postorder: ");
        obj.postorderRecursive(root);
        System.out.println();
        
        System.out.print("Iterative Postorder: ");
        obj.postorderIterative(root);
    }
}
