/**
 * a red-black tree implementation by the unavailable team!
 */
public class RedBlackTree {
    private boolean color;
    //rotations on red-black tree
    public void rotateLeft(RedBlackTree node) {

    }
    public void rotateRight(RedBlackTree Node) {

    }
    //operations on red-black tree
    public RedBlackTree searchNode(RedBlackTree Node) {
        return Node;
    }
    public RedBlackTree insertNode(RedBlackTree Node) {
        return Node;
    }
    public void deleteNode(RedBlackTree Node) {

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
    /**
     * @return BLACK: "true" or RED: "false"
     */



    public boolean getColor() {
        return color;
    }
    public String getColorToString() {
        if(this.getColor()) return "black";
        return "red";
    }
    /**
     * @param color BLACK: "true" or RED: "false"
     */
    public void setColor(boolean color) {
        this.color = color;
    }
}
