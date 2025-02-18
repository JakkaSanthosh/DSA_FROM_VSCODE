class TreeNode {
    int val;
    TreeNode left, right;
    
    public TreeNode(int val) {
        this.val = val;
        this.left = this.right = null;
    }
}

// Binary Search Tree Class
class BST{
    
    // Insert a node into BST
    public TreeNode insert(TreeNode root, int key) {
        if (root == null) return new TreeNode(key);
        
        if (key < root.val) 
            root.left = insert(root.left, key);
        else if (key > root.val) 
            root.right = insert(root.right, key);
        
        return root;
    }
    
    // Search for a node in BST
    public boolean search(TreeNode root, int key) {
        if (root == null) return false;
        if (root.val == key) return true;
        
        return (key < root.val) ? search(root.left, key) : search(root.right, key);
    }

    // Find the minimum value node in BST (used in delete operation)
    private TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) 
            current = current.left;
        return current;
    }
    
    // Delete a node from BST
    public TreeNode delete(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) 
            root.left = delete(root.left, key);
        else if (key > root.val) 
            root.right = delete(root.right, key);
        else {
            // Case 1: Node with only one child or no child
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            // Case 2: Node with two children, get the inorder successor (smallest in the right subtree)
            TreeNode temp = minValueNode(root.right);
            root.val = temp.val;
            root.right = delete(root.right, temp.val);
        }
        return root;
    }

    // Inorder Traversal (LNR) to display BST elements in sorted order
    public void inorder(TreeNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }
}

public class BSTOperations {
    public static void main(String[] args) {
        BST bst = new BST();
        TreeNode root = null;

        // Insert nodes
        int[] keys = {50, 30, 70, 20, 40, 60, 80};
        for (int key : keys) {
            root = bst.insert(root, key);
        }
        
        // Print inorder traversal
        System.out.print("Inorder traversal after insertion: ");
        bst.inorder(root);
        System.out.println();

        // Search for a node
        int searchKey = 40;
        System.out.println("Search " + searchKey + ": " + (bst.search(root, searchKey) ? "Found" : "Not Found"));

        // Delete a node
        int deleteKey = 50;
        root = bst.delete(root, deleteKey);
        System.out.print("Inorder traversal after deleting " + deleteKey + ": ");
        bst.inorder(root);
        System.out.println();
    }
}
