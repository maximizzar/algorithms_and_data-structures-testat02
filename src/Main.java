import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.BitSet;

public class Main {
    public static void main(String[] args) {
        Integer elementCount = 15,seed = 1337,maxRandomNumber = 100;
        
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

        int value = random.nextInt(maxRandomNumber) + 1;
        RedBlackTree redBlackTree = new RedBlackTree(value);

        Files.createDirectories(Path.of("rbt/"));

        for (int i = 0; i < elementCount;) {
            value = random.nextInt(maxRandomNumber);

            if (alreadySeen.get(value)) continue;

            stringBuilder = new StringBuilder();
            String path = "rbt/" + (i + 1) + ".dot";
            fileWriter = new FileWriter(path);

            alreadySeen.set(value); i++;
            redBlackTree.insertNode(value);

            roundHeadingComment(stringBuilder, (i + 1));
            currentDotDiagram(stringBuilder, redBlackTree);

            fileWriter.write(stringBuilder.toString());
            System.out.println(stringBuilder);
            fileWriter.close();
        }
    }

    //Extracted code
    private static void currentDotDiagram(StringBuilder stringBuilder, RedBlackTree tree) {
        stringBuilder.append("digraph RBTree {").append("\n");
        tree.toDOT(tree.root, stringBuilder);
        stringBuilder.append("\tnil [style = filled, fillcolor = black, fontcolor = white];")
                .append("\n")
                .append("}").append("\n\n");
    }
    private static void roundHeadingComment(StringBuilder stringBuilder, Integer i) {
        stringBuilder.append("// ")
                .append(" ".repeat(16))
                .append(" round: ").append(i)
                .append(" "). append("\n\n");
    }
}
