class BTreeNode {
    int[] keys;
    int t;
    BTreeNode[] children;
    int n;
    boolean leaf;

    BTreeNode(int t, boolean leaf) {
        this.t = t;
        this.leaf = leaf;
        this.keys = new int[2 * t - 1];
        this.children = new BTreeNode[2 * t];
        this.n = 0;
    }

    void traverse() {
        for (int i = 0; i < n; i++) {
            if (!leaf)
                children[i].traverse();
            System.out.print(keys[i] + " ");
        }
        if (!leaf)
            children[n].traverse();
    }

    BTreeNode search(int k) {
        int i = 0;
        while (i < n && k > keys[i])
            i++;

        if (i < n && keys[i] == k)
            return this;

        if (leaf)
            return null;

        return children[i].search(k);
    }

    void insertNonFull(int k) {
        int i = n - 1;

        if (leaf) {
            while (i >= 0 && keys[i] > k) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = k;
            n++;
        } else {
            while (i >= 0 && keys[i] > k)
                i--;

            i++; // ⭐ FIX: important safe index

            if (children[i].n == 2 * t - 1) {
                System.out.println("\n⚠ Node Overflow Detected!");
                System.out.println("📌 Lower Median " + children[i].keys[t - 1] + " moves upward");

                splitChild(i, children[i]);

                if (k > keys[i])
                    i++;
            }

            children[i].insertNonFull(k);
        }
    }

    void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.t, y.leaf);
        z.n = t - 1;

        for (int j = 0; j < t - 1; j++)
            z.keys[j] = y.keys[j + t];

        if (!y.leaf) {
            for (int j = 0; j < t; j++)
                z.children[j] = y.children[j + t];
        }

        y.n = t - 1;

        for (int j = n; j >= i + 1; j--)
            children[j + 1] = children[j];

        children[i + 1] = z;

        for (int j = n - 1; j >= i; j--)
            keys[j + 1] = keys[j];

        keys[i] = y.keys[t - 1];
        n++;
    }

    void rangeQuery(int low, int high) {
        for (int i = 0; i < n; i++) {
            if (!leaf)
                children[i].rangeQuery(low, high);

            if (keys[i] >= low && keys[i] <= high)
                System.out.print(keys[i] + " ");
        }

        if (!leaf)
            children[n].rangeQuery(low, high);
    }
}

class BTree {
    BTreeNode root;
    int t;

    BTree(int t) {
        this.t = t;
        this.root = null;
    }

    void insert(int k) {
        System.out.println("\n➡ Inserting Book ID: " + k);

        if (root == null) {
            root = new BTreeNode(t, true);
            root.keys[0] = k;
            root.n = 1;
            System.out.println("✔ Root created with " + k);
        } else {
            if (root.n == 2 * t - 1) {
                System.out.println("⚠ Root Overflow Detected!");
                System.out.println("📌 Lower Median moves upward");

                BTreeNode s = new BTreeNode(t, false);
                s.children[0] = root;
                s.splitChild(0, root);

                int i = 0;
                if (k > s.keys[0])
                    i++;

                s.children[i].insertNonFull(k);
                root = s;
            } else {
                root.insertNonFull(k);
            }
        }
    }

    void search(int k) {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }

        if (root.search(k) != null)
            System.out.println("✔ Book ID " + k + " FOUND");
        else
            System.out.println("✘ Book ID " + k + " NOT FOUND");
    }

    void traverse() {
        if (root != null)
            root.traverse();
    }

    void rangeQuery(int low, int high) {
        System.out.println("\n📚 Range Query [" + low + " - " + high + "]");
        if (root != null)
            root.rangeQuery(low, high);
        System.out.println();
    }

    void printTree() {
        System.out.println("\n🌳 Final B-Tree Structure:");
        traverse();
        System.out.println();
    }
}

public class LibraryHub {
    public static void main(String[] args) {

        BTree library = new BTree(2);

        int[] bookIDs = {10, 20, 30, 40, 50, 60, 70, 80, 90};

        System.out.println("===== LIBRARY HUB B-TREE SYSTEM =====");

        for (int id : bookIDs) {
            library.insert(id);
        }

        library.printTree();

        library.search(40);
        library.search(100);

        library.rangeQuery(30, 70);

        System.out.println("\n===== CASE STUDY CONCLUSION =====");
        System.out.println("✔ Node Overflow handled correctly");
        System.out.println("✔ Lower Median moves upward during split");
        System.out.println("✔ Balanced indexing maintained");
        System.out.println("✔ Efficient search and insertion");
    }
}