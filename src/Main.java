public class Main {
    public static void main(String[] args) {
        java.util.Random random = new java.util.Random();
        RedBlackTree redBlackTree = new RedBlackTree(randomNumber(random));
        RedBlackTree gredBlackTree = new RedBlackTree(randomNumber(random));
        redBlackTree.setColor(false);
        System.out.println("Hi Mom!");
        System.out.println(redBlackTree.getColorToString());

        System.out.println(redBlackTree.getValue());
    }
    public static int randomNumber(java.util.Random random) {

        return random.nextInt(99) + 1;
    }
}