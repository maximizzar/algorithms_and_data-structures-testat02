import java.util.Objects;

/**
 * a red-black tree implementation by the unavailable team!
 */

class Node {
    private Node parent, childLeft, childRight;
    protected Node root;
    private Integer value;
    private Boolean color;
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
        Node node = root, parent = null;
        while (Objects.nonNull(node)) {
            parent = node;
            if (key < node.getValue()) node = node.getChildLeft();
            else if (key > node.getValue()) node = node.getChildRight();
            else throw new IllegalArgumentException("BST already contains a node with key " + key);
        }
        Node newNode = new Node(key);
        newNode.setColorRed();
        if (Objects.isNull(parent)) root = newNode;
        else if (key < parent.getValue()) parent.setChildLeft(newNode);
        else parent.setChildRight(newNode);
        newNode.setParent(parent);

        fixRedBlackPropertiesAfterInsert(newNode);
    }
    private void fixRedBlackPropertiesAfterInsert(Node node) {
        Node parent = node.getParent();
        if (Objects.isNull(parent)) {
            node.setColorBlack();
            return;
        }
        if (parent.isColorBlack()) return;

        Node grandparent = parent.getParent();
        if (Objects.isNull(grandparent)) {
            parent.setColorBlack();
            return;
        }

        Node uncle = getUncle(parent);
        if (Objects.nonNull(uncle) && uncle.isColorRed()) {
            parent.setColorBlack();
            grandparent.setColorRed();
            uncle.setColorBlack();

            fixRedBlackPropertiesAfterInsert(grandparent);
        }
        else if (Objects.equals(parent,grandparent.getChildLeft())) {
            if (Objects.equals(node,parent.getChildRight())) {
                rotateLeft(parent);
                parent = node;
            }
            rotateRight(grandparent);
            parent.setColorBlack();
            grandparent.setColorRed();
        }
        else {
            if (Objects.equals(node,parent.getChildLeft())) {
                rotateRight(parent);
                parent = node;
            }
            rotateLeft(grandparent);
            parent.setColorBlack();
            grandparent.setColorRed();
        }
    }
    private Node getUncle(Node parent) {
        Node grandparent = parent.getParent();
        if (Objects.equals(grandparent.getChildLeft(),parent)) return grandparent.getChildRight();
        else if (Objects.equals(grandparent.getChildRight(),parent)) return grandparent.getChildLeft();
        else throw new IllegalStateException("Parent is not a child of its grandparent");
    }
    private void rotateRight(Node node) {
        Node parent = node.getParent();
        Node leftChild = node.getChildLeft();

        node.setChildLeft(leftChild.getChildRight());
        if (Objects.nonNull(leftChild.getChildRight())) {
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
        if (Objects.nonNull(rightChild.getChildLeft())) {
            rightChild.getChildLeft().setParent(node);
        }
        rightChild.setChildLeft(node);
        node.setParent(rightChild);

        replaceParentsChild(parent, node, rightChild);
    }
    private void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if (Objects.isNull(parent)) root = newChild;
        else if (Objects.equals(parent.getChildLeft(),oldChild)) parent.setChildLeft(newChild);
        else if (Objects.equals(parent.getChildRight(),oldChild)) parent.setChildRight(newChild);
        else throw new IllegalStateException("Node is not a child of its parent");

        if (Objects.nonNull(newChild)) newChild.setParent(parent);
    }
    public void toDOT(Node node, StringBuilder stringBuilder) {
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
        if (Objects.nonNull(node.getChildLeft())) {
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
        if (Objects.nonNull(node.getChildRight())) {
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
