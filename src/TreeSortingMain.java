/** Name - P.W.Achira Uwanpriya Senadheera
   IIT Id - 20232703
   UOW Id - w2082264
*/
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class TreeSortingMain {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java TreeSortingMain <inputFile>");
            System.exit(1);
        }
        String inputFile = args[0];
        try {
            // Announce input file usage
            System.out.println("Using command line input file: " + inputFile);
            String fullPath = new File(inputFile).getAbsolutePath();
            System.out.println("Processing file: " + fullPath + "\n");

            // Parse the initial tree
            TreeParser parser = new TreeParser();
            TreeState initial = parser.parse(inputFile);

            // Derive the target BST by sorting node values
            int[] sorted = initial.getNodes();
            Arrays.sort(sorted);
            TreeState goal = new TreeState(sorted);

            // Print input and target
            System.out.println("Input tree (level-order): " + initial);
            System.out.println("Target BST (level-order): " + goal + "\n");

            // Compute optimal transformation
            System.out.println("Finding optimal transformation...");
            AStarSearch search = new AStarSearch();
            List<SwapOperation> moves = search.solve(initial, goal);

            // Print the optimal swap sequence using values
            OutputHandler output = new OutputHandler();
            output.printMoves(moves, initial, goal);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
