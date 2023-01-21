import java.io.FileWriter;
import java.io.IOException;
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
        try {
            FileWriter fileWriter = new FileWriter("src/RedBlackTree.dot");
            fileWriter.write(outputRedBlackTreeWithRandomElements(elementCount,seed,maxRandomNumber));
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String outputRedBlackTreeWithRandomElements(Integer elementCount,
                                                             Integer seed,
                                                             Integer maxRandomNumber) {

        BitSet alreadySeen = new BitSet(maxRandomNumber);
        Random random = new Random(seed);
        StringBuilder stringBuilder = new StringBuilder();

        Integer value = random.nextInt(maxRandomNumber) + 1;
        RedBlackTree tree = new RedBlackTree(value);

        stringBuilder.append("//RedBack_")
                .append(elementCount)
                .append("_elements")
                .append("\n");

        for (int i = 0; i < elementCount;) {
            value = random.nextInt(maxRandomNumber);

            if (!alreadySeen.get(value)) {
                alreadySeen.set(value); i++;
                tree.insertNode(value);

                stringBuilder.append("// ")
                        .append(" ".repeat(16))
                        .append(" round: ").append(i).append(" "). append("\n\n");

                stringBuilder.append("digraph RBTree {").append("\n");
                tree.toDOT(tree.root,stringBuilder);
                stringBuilder.append("\tnil [style = filled, fillcolor = black, fontcolor = white];")
                        .append("\n")
                        .append("}").append("\n\n");
            }
        }
        return stringBuilder.toString();
    }
}
