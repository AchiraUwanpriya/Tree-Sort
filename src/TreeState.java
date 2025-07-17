/** Name - P.W.Achira Uwanpriya Senadheera
   IIT Id - 20232703
   UOW Id - w2082264
*/
import java.util.*;

public class TreeState {
    private final int[] nodes;

    public TreeState(int[] nodes) {
        this.nodes = Arrays.copyOf(nodes, nodes.length);
    }

    public int[] getNodes() {
        return Arrays.copyOf(nodes, nodes.length);
    }

    // Generate all valid swaps (child with its parent).
    public List<SwapOperation> getPossibleSwaps() {
        List<SwapOperation> ops = new ArrayList<>();
        for (int i = 1; i < nodes.length; i++) {
            int p = (i - 1) / 2;
            ops.add(new SwapOperation(i, p));
        }
        return ops;
    }

    // Apply a swap and return the new TreeState. */
    public TreeState apply(SwapOperation op) {
        int[] copy = Arrays.copyOf(nodes, nodes.length);
        int tmp = copy[op.getIndex()];
        copy[op.getIndex()] = copy[op.getParentIndex()];
        copy[op.getParentIndex()] = tmp;
        return new TreeState(copy);
    }

    @Override
    public boolean equals(Object o) {
        return (o instanceof TreeState) && Arrays.equals(nodes, ((TreeState)o).nodes);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(nodes);
    }

    @Override
    public String toString() {
        return Arrays.toString(nodes);
    }
}