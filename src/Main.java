public class Main {
    public static void main(String[] args) {
        java.util.Random random = new java.util.Random();
        RedBlackTree redBlackTree = new RedBlackTree(randomNumber());
        RedBlackTree gredBlackTree = new RedBlackTree(randomNumber());
        redBlackTree.setColor(false);
        System.out.println("Hi Mom!");
        System.out.println(redBlackTree.getColorToString());

        System.out.println(redBlackTree.getValue());
    }
    public static Integer randomNumber() {
        return java.util.Random.from(Main::randomNumber).nextInt(99) + 1;
    }
}
