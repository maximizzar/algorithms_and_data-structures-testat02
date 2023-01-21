/**
 * a red-black tree implementation by the unavailable team!
 */
class Node {
    private Node parent, childLeft, childRight;
    protected Node root;
    private Integer value;
    private boolean color;

    public Node(Integer value) {
        setValue(value);
    }

    public Node getChildLeft() {
        return childLeft;
    }

    public Node getChildRight() {
        return childRight;
    }

    public Node getParent() {
        return parent;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isColorBlack() {
        return color;
    }

    public boolean isColorRed() {
        return !color;
    }

    public void setChildLeft(Node childLeft) {
        this.childLeft = childLeft;
    }

    public void setChildRight(Node childRight) {
        this.childRight = childRight;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public void setColorBlack() {
        this.color = true;
    }

    public void setColorRed() {
        this.color = false;
    }
}
public class RedBlackTree extends Node {

    public RedBlackTree(Integer value) {
        super(value);
    }
    public void insertNode(int key) {
        Node node = root;
        Node parent = null;

        // Traverse the tree to the left or right depending on the key
        while (node != null) {
            parent = node;
            if (key < node.getValue()) {
                node = node.getChildLeft();
            } else if (key > node.getValue()) {
                node = node.getChildRight();
            } else {
                throw new IllegalArgumentException("BST already contains a node with key " + key);
            }
        }

        // Insert new node
        Node newNode = new Node(key);
        newNode.setColorRed();
        if (parent == null) {
            root = newNode;
        } else if (key < parent.getValue()) {
            parent.setChildLeft(newNode);
        } else {
            parent.setChildRight(newNode);
        }
        newNode.setParent(parent);

        fixRedBlackPropertiesAfterInsert(newNode);
    }

    @SuppressWarnings("squid:S125") // Ignore SonarCloud complains about commented code line 70.
    private void fixRedBlackPropertiesAfterInsert(Node node) {
        Node parent = node.getParent();

        // Case 1: Parent is null, we've reached the root, the end of the recursion
        if (parent == null) {
            // Uncomment the following line if you want to enforce black roots (rule 2):
            node.setColorBlack();
            return;
        }

        // Parent is black --> nothing to do
        if (parent.isColorBlack()) {
            return;
        }

        // From here on, parent is red
        Node grandparent = parent.getParent();

        // Case 2:
        // Not having a grandparent means that parent is the root. If we enforce black roots
        // (rule 2), grandparent will never be null, and the following if-then block can be
        // removed.
        if (grandparent == null) {
            // As this method is only called on red nodes (either on newly inserted ones - or -
            // recursively on red grandparents), all we have to do is to recolor the root black.
            parent.setColorBlack();
            return;
        }

        // Get the uncle (may be null/nil, in which case its color is BLACK)
        Node uncle = getUncle(parent);

        // Case 3: Uncle is red -> recolor parent, grandparent and uncle
        if (uncle != null && uncle.isColorRed()) {
            parent.setColorBlack();
            grandparent.setColorRed();
            uncle.setColorBlack();

            // Call recursively for grandparent, which is now red.
            // It might be root or have a red parent, in which case we need to fix more...
            fixRedBlackPropertiesAfterInsert(grandparent);
        }

        // Note on performance:
        // It would be faster to do the uncle color check within the following code. This way
        // we would avoid checking the grandparent-parent direction twice (once in getUncle()
        // and once in the following else-if). But for better understanding of the code,
        // I left the uncle color check as a separate step.

        // Parent is left child of grandparent
        else if (parent == grandparent.getChildLeft()) {
            // Case 4a: Uncle is black and node is left->right "inner child" of its grandparent
            if (node == parent.getChildRight()) {
                rotateLeft(parent);

                // Let "parent" point to the new root node of the rotated sub-tree.
                // It will be recolored in the next step, which we're going to fall-through to.
                parent = node;
            }

            // Case 5a: Uncle is black and node is left->left "outer child" of its grandparent
            rotateRight(grandparent);

            // Recolor original parent and grandparent
            parent.setColorBlack();
            grandparent.setColorRed();
        }

        // Parent is right child of grandparent
        else {
            // Case 4b: Uncle is black and node is right->left "inner child" of its grandparent
            if (node == parent.getChildLeft()) {
                rotateRight(parent);

                // Let "parent" point to the new root node of the rotated sub-tree.
                // It will be recolored in the next step, which we're going to fall-through to.
                parent = node;
            }

            // Case 5b: Uncle is black and node is right->right "outer child" of its grandparent
            rotateLeft(grandparent);

            // Recolor original parent and grandparent
            parent.setColorBlack();
            grandparent.setColorRed();
        }
    }
    private Node getUncle(Node parent) {
        Node grandparent = parent.getParent();
        if (grandparent.getChildLeft() == parent) {
            return grandparent.getChildRight();

        } else if (grandparent.getChildRight() == parent) {
            return grandparent.getChildLeft();
        } else {
            throw new IllegalStateException("Parent is not a child of its grandparent");
        }
    }
    private void rotateRight(Node node) {
        Node parent = node.getParent();
        Node leftChild = node.getChildLeft();

        node.setChildLeft(leftChild.getChildRight());
        if (leftChild.getChildRight() != null) {
            leftChild.getChildRight().setParent(node);
        }

        leftChild.setChildRight(node);
        node.setParent(leftChild);

        replaceParentsChild(parent, node, leftChild);
    }
    private void rotateLeft(Node node) {
        Node parent = node.getParent();
        Node rightChild = node.getChildRight();

        node.setChildRight(rightChild.getChildLeft());
        if (rightChild.getChildLeft() != null) {
            rightChild.getChildLeft().setParent(node);
        }

        rightChild.setChildLeft(node);
        node.setParent(rightChild);

        replaceParentsChild(parent, node, rightChild);
    }
    private void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if (parent == null) {
            root = newChild;
        } else if (parent.getChildLeft() == oldChild) {
            parent.setChildLeft(newChild);
        } else if (parent.getChildRight() == oldChild) {
            parent.setChildRight(newChild);
        } else {
            throw new IllegalStateException("Node is not a child of its parent");
        }

        if (newChild != null) {
            newChild.setParent(parent);
        }
    }

    public String toString() {
        return "";
    }
    public void toDOT(Node node, StringBuilder stringBuilder) {
        //output function in DOT format.

        if (node.isColorRed()) { // coloring
            stringBuilder.append("\t")
                    .append(node.getValue())
                    .append(" [style = filled, fillcolor = red];")
                    .append("\n");
        } else {
            stringBuilder.append("\t")
                    .append(node.getValue())
                    .append(" [style = filled, fillcolor = black, fontcolor = white];")
                    .append("\n");
        }

        if (node.getChildLeft() != null) {
            stringBuilder.append("\t")
                    .append(node.getValue())
                    .append(" -> ")
                    .append(node.getChildLeft().getValue())
                    .append(" [label = \" left\"];")
                    .append("\n");

            toDOT(node.getChildLeft(),stringBuilder);
        } else {
            stringBuilder.append("\t")
                    .append(node.getValue())
                    .append(" -> nil [label = \" left\"];")
                    .append("\n");
        }

        if (node.getChildRight() != null) {
            stringBuilder.append("\t")
                    .append(node.getValue())
                    .append(" -> ")
                    .append(node.getChildRight().getValue())
                    .append(" [label = \" right\"];")
                    .append("\n");

            toDOT(node.getChildRight(),stringBuilder);
        } else {
            stringBuilder.append("\t")
                    .append(node.getValue())
                    .append(" -> nil [label = \" right\"];")
                    .append("\n");
        }
    }
}
