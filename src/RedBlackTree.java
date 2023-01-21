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

    //operations on red-black tree
    public void insertNode(Integer value) {
        Node rbtNode = root;
        Node parent = null;

        while (rbtNode != null) {
            parent = rbtNode;

            if (value < rbtNode.getValue()) rbtNode = rbtNode.getChildLeft();
            else if (value > rbtNode.getValue()) rbtNode = rbtNode.getChildRight();
            else throw new IllegalArgumentException("node with key already present! " + value);
        }

        Node newRbtNode = new Node(value);
        newRbtNode.setColorRed();

        if (parent == null) root = newRbtNode;
        else if (value < parent.getValue()) parent.setChildLeft(newRbtNode);
        else parent.setChildRight(newRbtNode);
        newRbtNode.setParent(parent);
        fixRBTPropertiesInsert(newRbtNode);
    }

    //rotations on red-black tree
    public void rotateLeft(Node rbtNode) {
        Node parent = rbtNode.getParent();
        Node rightChild = rbtNode.getChildRight();

        rbtNode.setChildRight(rightChild.getChildLeft());
        if (rightChild.getChildLeft() != null) {
            rightChild.getChildLeft().setParent(rbtNode);
        }
        rightChild.setChildLeft(rightChild);
        rbtNode.setParent(rightChild);

        replaceParentsChild(parent, rbtNode, rightChild);
    }

    public void rotateRight(Node rbtNode) {
        Node parent = rbtNode.getParent();
        Node leftChild = rbtNode.getChildLeft();

        rbtNode.setChildLeft(leftChild.getChildRight());
        if (leftChild.getChildRight() != null) {
            leftChild.getChildRight().setParent(rbtNode);
        }
        leftChild.setChildRight(rbtNode);
        rbtNode.setParent(leftChild);

        replaceParentsChild(parent, rbtNode, leftChild);
    }

    public void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if (java.util.Objects.equals(parent, null)) root = newChild;
        else if (java.util.Objects.equals(parent.getChildLeft(), oldChild)) parent.setChildLeft(newChild);
        else if (java.util.Objects.equals(parent.getChildRight(), parent)) parent.setChildRight(newChild);
        else throw new IllegalStateException("Node is not a child of its parent");

        if (newChild != null) newChild.setParent(parent);
    }

    //helper methods
    private void fixRBTPropertiesInsert(Node rbtNode) {
        Node parent = rbtNode.getParent();

        if (parent == null) {
            rbtNode.setColorBlack();
            return; //Parent is null, the end of the recursion
        }
        if (parent.isColorBlack()) return;

        //if parent is red
        Node grandparent = parent.getParent(); //Frisisch: greandparten

        if (grandparent == null) {
            parent.setColorBlack();
            return;
        }
        Node RtheFruitDude;
        Node LtheFruitDude;
        //theFruitDude either nil or black
        if (grandparent.getChildLeft() == parent.getParent()) {
            RtheFruitDude = grandparent.getChildRight();
            if (grandparent.getChildRight() == parent.getParent()) {
                LtheFruitDude = grandparent.getChildLeft();


                //Right-Uncle is red
                if (RtheFruitDude != null && RtheFruitDude.isColorRed()) {
                    parent.setColorBlack();
                    grandparent.setColorRed();
                    RtheFruitDude.setColorBlack();

                    //recoloring grandparent may break RBTProperties
                    fixRBTPropertiesInsert(grandparent);

                }
                //Left-Uncle is red
                if (LtheFruitDude != null && LtheFruitDude.isColorRed()) {
                    parent.setColorBlack();
                    grandparent.setColorRed();
                    LtheFruitDude.setColorBlack();

                    //recoloring grandparent may break RBTProperties
                    fixRBTPropertiesInsert(grandparent);

                } else if (parent == grandparent.getChildLeft()) { //Parent is left child of grandparent
                    if (rbtNode == parent.getChildRight()) {
                        rotateLeft(parent);
                        parent = rbtNode;
                    }
                    rotateRight(grandparent);

                    //the former parent and grandparent
                    parent.setColorBlack();
                    grandparent.setColorRed();

                } else { //Parent is right child of grandparent
                    if (rbtNode == parent.getChildLeft()) {
                        rotateRight(parent);

                        parent = rbtNode;
                    }

                    rotateLeft(grandparent);

                    //the former parent and grandparent
                    parent.setColorBlack();
                    grandparent.setColorRed();
                }

            }
        }
    }
                public String toDot() {
                return null;
                }


}
