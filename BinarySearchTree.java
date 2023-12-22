import java.util.Vector;
import java.util.Iterator;

class BinarySearchTree<E extends Comparable<? super E>> extends BinaryTree<E> {
    private Node<E> findIOP(Node<E> curr) {
        curr = curr.left;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }
    public int height() {
        return height(root);
    }
    public E inOrderMin(Node <E> root) {
        E min = root.data;
        while (root.left != null) {
            min = root.left.data;
            root = root.left;
        }
        return min;
    }
    private int height(Node<E> curr) {
        if (curr == null) {
            return 0;
        }
        int left = height(curr.left) + 1;
        int right = height(curr.right) + 1;
        return left > right ? left : right;
    }
    public void insert(E data) {
        if (root == null) {
            root = new Node (data);
        }
        else {
            insert(root, data);
        }
    }
    public Node insert(Node<E> root, E data) {
        if (data.compareTo(root.data) <= 0) {
            if (root.left != null) {
                root.left = insert(root.left, data);
            }
            else {
                root.left = new Node (data);
            }
        }
        else if (data.compareTo(root.data) > 0) {
            if (root.right != null) {
                root.right = insert(root.right, data);
            }
            else {
                root.right = new Node (data);
            }
        }
        return root;
    }

    public Iterator<E> iterator() {
        vector = new Vector<E>();
        traverse(root);
        return vector.iterator();
    }
    public void remove(E data) {
        root = remove(root, data);
    }
    public Node remove(Node<E> root, E data) {
        if (root == null) {
            return root;
        }
        if(data.compareTo(root.data) < 0) {
            root.left = remove(root.left, data);
        }
        else if (data.compareTo(root.data) > 0) {
            root.right = remove(root.right, data);
        }
        else {
            if(root.left == null) {
                return root.right;
            }
            else if (root.right == null) {
                return root.left;
            }
            root.data = inOrderMin(root.right);
            root.right = remove(root.right, root.data);
        }
        return root;
    }

    public boolean search(E data) {
        return search(root, data);
    }
    public boolean search(Node<E> root, E data){
        if (root == null) {
            return false;
        }
        if (data.compareTo(root.data) == 0) {
            return true;
        }
        if (data.compareTo(root.data) < 0) {
            return search(root.left, data);
        }
        if (data.compareTo(root.data) > 0) {
            return search(root.right, data);
        }
        return false;
    }
    private void traverse(Node<E> curr) {
        if (curr != null) {
            traverse(curr.left);
            vector.add(curr.data);
            traverse(curr.right);
        }
    }
    private Vector<E> vector;

}
