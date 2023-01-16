/**
 * a red-black tree implementation by the unavailable team!
 */
public class RedBlackTree {
    private RedBlackTree parent, childLeft, childRight;
    RedBlackTree root;
    private Integer value;
    private boolean color;

    public RedBlackTree(Integer value) {
        setValue(value);
    }

    //rotations on red-black tree
    public void rotateLeft(RedBlackTree rbtNode) {
        RedBlackTree parent = rbtNode.getParent();
        RedBlackTree rightChild = rbtNode.childRight.getChildRight();

        rbtNode.setChildRight(rightChild.getChildLeft());
        if(rightChild.getChildLeft() != null) {
            rightChild.getChildLeft().setParent(rbtNode);
        }
        rightChild.setChildLeft(rightChild);
        rbtNode.setParent(rightChild);

        replaceParentsChild(parent,rbtNode,rightChild);
    }

    public void rotateRight(RedBlackTree rbtNode) {
        RedBlackTree parent = rbtNode.getParent();
        RedBlackTree leftChild = rbtNode.getChildLeft();

        rbtNode.setChildLeft(leftChild.getChildRight());
        if(leftChild.getChildRight() != null) {
            leftChild.getChildRight().setParent(rbtNode);
        }
        leftChild.setChildRight(rbtNode);
        rbtNode.setParent(leftChild);

        replaceParentsChild(parent,rbtNode,leftChild);
    }

    public void replaceParentsChild(RedBlackTree parent, RedBlackTree oldChild, RedBlackTree newChild) {
        if(java.util.Objects.equals(parent,null)) root = newChild;
        else if (java.util.Objects.equals(parent.getChildLeft(), oldChild)) parent.setChildLeft(newChild);
        else if (java.util.Objects.equals(parent.getChildRight(),parent)) parent.setChildRight(newChild);
        else throw new IllegalStateException("Node is not a child of its parent");

        if(newChild != null) newChild.setParent(parent);
    }

    //operations on red-black tree
    public RedBlackTree insertNode() {
        return null;
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

    public RedBlackTree getChildLeft() {return childLeft;}
    public void setChildLeft(RedBlackTree childLeft) {this.childLeft = childLeft;}
    public RedBlackTree getChildRight() {return childRight;}

    public void setChildRight(RedBlackTree childRight) {this.childRight = childRight;}
    public RedBlackTree getParent() {return parent;}
    public void setParent(RedBlackTree parent) {this.parent = parent;}
    public Integer getValue() {return value;}
    public void setValue(Integer value) {this.value = value;}
    public boolean getColor() {return color;}
    public String getColorToString() {
        if(this.getColor()) return "black";
        return "red";
    }
    public void setColor(boolean color) {this.color = color;}
}
