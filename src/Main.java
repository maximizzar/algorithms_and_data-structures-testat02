import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.BitSet;

public class Main {
    public static void main(String[] args) {
        FileWriter fileWriter;
        Integer elementCount,seed,maxRandomNumber;
            elementCount = 15;
            seed = 1337;
            maxRandomNumber = 100;

        try {
            outputRBTWithRandomElements(elementCount,seed,maxRandomNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void outputRBTWithRandomElements(Integer elementCount, Integer seed, Integer maxRandomNumber)
            throws IOException {
        BitSet alreadySeen = new BitSet(maxRandomNumber);
        Random random = new Random(seed);
        StringBuilder stringBuilder;
        FileWriter fileWriter;

        Integer value = random.nextInt(maxRandomNumber) + 1;
        RedBlackTree tree = new RedBlackTree(value);

        for (int i = 0; i < elementCount;) {
            value = random.nextInt(maxRandomNumber);

            if (alreadySeen.get(value)) continue;

            stringBuilder = new StringBuilder();
            String path = "rbt/" + i + ".dot";
            fileWriter = new FileWriter(path);

            alreadySeen.set(value); i++;
            tree.insertNode(value);

            roundHeadingComment(stringBuilder, i);
            currentDotDiagram(stringBuilder, tree);

            fileWriter.write(stringBuilder.toString());
            fileWriter.close();
        }
    }
    private static void currentDotDiagram(StringBuilder stringBuilder, RedBlackTree tree) {
        stringBuilder.append("digraph RBTree {").append("\n");
        tree.toDOT(tree.root, stringBuilder);
        stringBuilder.append("\tnil [style = filled, fillcolor = black, fontcolor = white];")
                .append("\n")
                .append("}").append("\n\n");
    }
    private static void roundHeadingComment(StringBuilder stringBuilder, int i) {
        stringBuilder.append("// ")
                .append(" ".repeat(16))
                .append(" round: ").append(i)
                .append(" "). append("\n\n");
    }
}
