/** Name - P.W.Achira Uwanpriya Senadheera
   IIT Id - 20232703
   UOW Id - w2082264
*/
import java.util.*;

public class AStarSearch {
    public List<SwapOperation> solve(TreeState start, TreeState goal) {
        Map<TreeState, Integer> gScore = new HashMap<>();
        Map<TreeState, SearchNode> allNodes = new HashMap<>();
        PriorityQueue<SearchNode> open = new PriorityQueue<>(Comparator.comparingInt(n -> n.f));

        SearchNode startNode = new SearchNode(start, null, null, 0, heuristic(start, goal));
        open.add(startNode);
        allNodes.put(start, startNode);
        gScore.put(start, 0);

        while (!open.isEmpty()) {
            SearchNode current = open.poll();
            if (current.state.equals(goal)) {
                return reconstructPath(current);
            }
            for (SwapOperation op : current.state.getPossibleSwaps()) {
                TreeState neighbor = current.state.apply(op);
                int tentativeG = current.g + 1;
                int neighborG = gScore.getOrDefault(neighbor, Integer.MAX_VALUE);
                if (tentativeG < neighborG) {
                    int h = heuristic(neighbor, goal);
                    SearchNode neighborNode = new SearchNode(neighbor, current, op, tentativeG, h);
                    gScore.put(neighbor, tentativeG);
                    open.remove(allNodes.get(neighbor));
                    open.add(neighborNode);
                    allNodes.put(neighbor, neighborNode);
                }
            }
        }
        return Collections.emptyList();
    }

    private int heuristic(TreeState s, TreeState goal) {
        int[] a = s.getNodes();
        int[] b = goal.getNodes();
        int count = 0;
        for (int i = 0; i < Math.min(a.length, b.length); i++) {
            if (a[i] != b[i]) count++;
        }
        return count;
    }

    private List<SwapOperation> reconstructPath(SearchNode node) {
        List<SwapOperation> path = new ArrayList<>();
        while (node.move != null) {
            path.add(node.move);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }

    private class SearchNode {
        TreeState state;
        SearchNode parent;
        SwapOperation move;
        int g;
        int f;

        SearchNode(TreeState state, SearchNode parent, SwapOperation move, int g, int h) {
            this.state = state;
            this.parent = parent;
            this.move = move;
            this.g = g;
            this.f = g + h;
        }
    }
}