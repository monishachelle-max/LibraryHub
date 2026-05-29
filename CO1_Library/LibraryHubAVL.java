class Node {

    int bookId, height;
    Node left, right;

    Node(int id) {
        bookId = id;
        height = 1;
    }
}

public class LibraryHubAVL {

    Node root;

    int height(Node n) {

        if (n == null)
            return 0;

        return n.height;
    }

    int getBalance(Node n) {

        if (n == null)
            return 0;

        return height(n.left) - height(n.right);
    }

    Node rightRotate(Node y) {

        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;

        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {

        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;

        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int key) {

        if (node == null)
            return new Node(key);

        if (key < node.bookId)
            node.left = insert(node.left, key);

        else if (key > node.bookId)
            node.right = insert(node.right, key);

        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && key < node.left.bookId)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.bookId)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.bookId) {

            node.left = leftRotate(node.left);

            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.bookId) {

            node.right = rightRotate(node.right);

            return leftRotate(node);
        }

        return node;
    }

    Node minValueNode(Node node) {

        Node current = node;

        while (current.left != null)
            current = current.left;

        return current;
    }

    Node deleteNode(Node root, int key) {

        if (root == null)
            return root;

        if (key < root.bookId)
            root.left = deleteNode(root.left, key);

        else if (key > root.bookId)
            root.right = deleteNode(root.right, key);

        else {

            if ((root.left == null) || (root.right == null)) {

                Node temp;

                if (root.left != null)
                    temp = root.left;
                else
                    temp = root.right;

                if (temp == null) {

                    temp = root;
                    root = null;
                }
                else
                    root = temp;
            }
            else {

                Node temp = minValueNode(root.right);

                root.bookId = temp.bookId;

                root.right = deleteNode(root.right, temp.bookId);
            }
        }

        if (root == null)
            return root;

        root.height = Math.max(height(root.left),
                height(root.right)) + 1;

        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {

            root.left = leftRotate(root.left);

            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {

            root.right = rightRotate(root.right);

            return leftRotate(root);
        }

        return root;
    }

    void inorder(Node node) {

        if (node != null) {

            inorder(node.left);

            System.out.print(node.bookId + " ");

            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        LibraryHubAVL tree = new LibraryHubAVL();

        int books[] = {
            20, 30, 35, 40, 45,
            50, 60, 65, 70, 75,
            80, 85, 90
        };

        for (int id : books) {

            tree.root = tree.insert(tree.root, id);
        }

        // Original AVL Tree
        System.out.println("AVL Tree after Insertions:");
        tree.inorder(tree.root);

        // Delete 30
        tree.root = tree.deleteNode(tree.root, 30);

        System.out.println("\n\nAVL Tree after Deleting 30:");
        tree.inorder(tree.root);

        // Delete 70
        tree.root = tree.deleteNode(tree.root, 70);

        System.out.println("\n\nAVL Tree after Deleting 70:");
        tree.inorder(tree.root);

        // Delete 50
        tree.root = tree.deleteNode(tree.root, 50);

        System.out.println("\n\nAVL Tree after Deleting 50:");
        tree.inorder(tree.root);
    }
}