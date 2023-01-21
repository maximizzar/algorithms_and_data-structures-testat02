import java.util.Random;
import java.util.BitSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer elementCount,seed,maxRandomNumber;

        Boolean test = true;

        if(test) {
            elementCount = 15;
            seed = 1337;
            maxRandomNumber = 100;

        } else {
            elementCount = scanner.nextInt();
            seed = scanner.nextInt();
            maxRandomNumber = scanner.nextInt();
        }

        outputRedBlackTreeWithRandomElements(elementCount,seed,maxRandomNumber);
    }
    public static void outputRedBlackTreeWithRandomElements(Integer elementCount,
                                                             Integer seed,
                                                             Integer maxRandomNumber) {

        BitSet alreadySeen = new BitSet(maxRandomNumber);
        Random random = new Random(seed);

        Integer value = random.nextInt(maxRandomNumber) + 1;
        RedBlackTree tree = new RedBlackTree(value);

        System.out.println("//RedBack_" + elementCount + "_elements");

        for (int i = 0; i < elementCount;) {
            value = random.nextInt(maxRandomNumber);

            if (!alreadySeen.get(value)) {
                alreadySeen.set(value);
                i++;
                tree.insertNode(value);
            }
        }
        tree.toDOT();
    }
}
