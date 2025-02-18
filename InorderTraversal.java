import java.util.*;

// TreeNode class definition
class TreeNode {
    int val;
    TreeNode left, right;
    
    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class InorderTraversal {
    // Recursive Inorder
    void inorderRecursive(TreeNode root) {
        if (root != null) {
            inorderRecursive(root.left);
            System.out.print(root.val + " ");
            inorderRecursive(root.right);
        }
    }

    // Iterative Inorder using Stack
    void inorderIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
    }

    public static void main(String[] args) {
        // Create the tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        
        // Create an object of InorderTraversal class
        InorderTraversal obj = new InorderTraversal();

        // Perform Recursive Inorder Traversal
        System.out.print("Recursive Inorder: ");
        obj.inorderRecursive(root);
        System.out.println();
        
        // Perform Iterative Inorder Traversal
        System.out.print("Iterative Inorder: ");
        obj.inorderIterative(root);
    }
}
