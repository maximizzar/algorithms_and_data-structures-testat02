/**
 * a red-black tree implementation by the unavailable team!
 */
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
        if(rightChild.getChildLeft() != null) {
            rightChild.getChildLeft().setParent(rbtNode);
        }
        rightChild.setChildLeft(rightChild);
        rbtNode.setParent(rightChild);

        replaceParentsChild(parent,rbtNode,rightChild);
    }

    public void rotateRight(Node rbtNode) {
        Node parent = rbtNode.getParent();
        Node leftChild = rbtNode.getChildLeft();

        rbtNode.setChildLeft(leftChild.getChildRight());
        if(leftChild.getChildRight() != null) {
            leftChild.getChildRight().setParent(rbtNode);
        }
        leftChild.setChildRight(rbtNode);
        rbtNode.setParent(leftChild);

        replaceParentsChild(parent,rbtNode,leftChild);
    }

    public void replaceParentsChild(Node parent, Node oldChild, Node newChild) {
        if(java.util.Objects.equals(parent,null)) root = newChild;
        else if (java.util.Objects.equals(parent.getChildLeft(), oldChild)) parent.setChildLeft(newChild);
        else if (java.util.Objects.equals(parent.getChildRight(),parent)) parent.setChildRight(newChild);
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
        //theFruitDude either nil or black
        Node theFruitDude = getTheFruitDude(parent);

        //Uncle is red
        if (theFruitDude != null && theFruitDude.isColorRed()) {
            parent.setColorBlack();
            grandparent.setColorRed();
            theFruitDude.setColorBlack();

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

    private Node getTheFruitDude(Node parent) {
        Node grandparent = parent.getParent().getParent();

        if(grandparent.getChildLeft() == parent.getParent()) return grandparent.getChildRight();
        else if (grandparent.getChildRight() == parent.getParent()) return grandparent.getChildLeft();
        throw new IllegalStateException("Parent is not a child of its grandparent");
    }

    public String toString() {
        //output function as Simple String.
        return "";
    }
    /**
     * @return a string which is PowerPoint DOT compatible.
     */
    public String toDOT() {
        //output function in DOT format.
        return "";
    }
    public String rbtNodeToDot(Node rbtNode) {
        //this-value
        //parent-value
        //childLeft-value
        //childRight-value
        return "";
    }
}

class Node {
    private Node parent, childLeft, childRight;
    protected Node root;
    private Integer value;
    private boolean color;

    public Node (Integer value) {
        setValue(value);
    }

    public Node getChildLeft() {return childLeft;}
    public Node getChildRight() {return childRight;}
    public Node getParent() {return parent;}
    public Integer getValue() {return value;}
    public boolean isColorBlack() {return color;}
    public boolean isColorRed() {return !color;}
    public void setChildLeft(Node childLeft) {this.childLeft = childLeft;}
    public void setChildRight(Node childRight) {this.childRight = childRight;}
    public void setParent(Node parent) {this.parent = parent;}
    public void setValue(Integer value) {this.value = value;}
    public void setColorBlack() {this.color = true;}
    public void setColorRed() {this.color = false;}
}