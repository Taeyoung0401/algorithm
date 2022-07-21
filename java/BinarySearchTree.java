public class BST {

    class Node {
        int value;
        Node left;
        Node right;
        Node(int value) {
            this.value = value;
        }
    }

    int[] input;
    Node root;

    public BST(int[] input) {
        this.input = input;
    }

    public Node search(int val) {
        return search(root, val);
    }

    public void insert(int val) {
        root = insert(root, val);
    }

    public void delete(int val) {
        root = delete(root, val);
    }

    private Node delete(Node node, int val) {
        if(node==null) {
            return null;
        }
        if(node.value<val) {
            node.right = delete(node.right, val);
        } else if(node.value>val) {
            node.left = delete(node.left, val);
        } else {
            // delete a node
            if(node.left==null && node.right==null) {
                return null;
            } else if(node.left==null) {
                return node.right;
            } else if(node.right==null) {
                return node.left;
            } else {
                Node min = findMin(node.right);
                node.value = min.value;
                node.right = delete(node.right, min.value);
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while(node.left!=null) {
            node = node.left;
        }
        return node;
    }

    private Node search(Node current, int val) {
        if(current==null || current.value==val) {
            return current;
        }
        if(val>current.value) {
            return search(current.right, val);
        } else {
            return search(current.left, val);
        }
    }

    private Node insert(Node current, int val) {
        if(current==null) {
            return new Node(val);
        } else if(val>current.value) {
            current.right = insert(current.right, val);
        } else if(val<=current.value) {
            current.left = insert(current.left, val);
        }
        return current;
    }



}
