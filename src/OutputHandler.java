/** Name - P.W.Achira Uwanpriya Senadheera
   IIT Id - 20232703
   UOW Id - w2082264
*/
import java.util.List;

public class OutputHandler {

     // Prints each swap step using the actual values being swapped, not indices.

    public void printMoves(List<SwapOperation> moves, TreeState initial, TreeState goal) {
        System.out.println("Initial state: " + initial);
        TreeState current = initial;
        int step = 1;
        for (SwapOperation op : moves) {
            int[] nodes = current.getNodes();
            int childVal = nodes[op.getIndex()];
            int parentVal = nodes[op.getParentIndex()];
            current = current.apply(op);
            System.out.printf("Step %3d: Swap value %d with parent value %d -> %s%n",
                    step++, childVal, parentVal, current);
        }
        System.out.println("Goal state reached: " + goal);
        System.out.println("Total moves: " + moves.size());
    }
}