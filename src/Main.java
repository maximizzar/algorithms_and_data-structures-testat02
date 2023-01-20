import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random random = new Random();
        RedBlackTree redBlackTree = new RedBlackTree(randomNumber(random));
        System.out.println(redBlackTree.rbtNodeToDot(redBlackTree));
    }
    public static Integer randomNumber(Random random) {
        return random.nextInt(99) + 1;
    }
}
