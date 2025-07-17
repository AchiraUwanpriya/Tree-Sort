/** Name - P.W.Achira Uwanpriya Senadheera
   IIT Id - 20232703
   UOW Id - w2082264
*/
import java.io.*;
import java.util.*;

public class TreeParser {

    // Parses whitespace-separated integers from a file into a complete TreeState.

    public TreeState parse(String filename) throws IOException {
        List<Integer> values = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                for (String token : line.trim().split("\\s+")) {
                    if (!token.isEmpty()) {
                        values.add(Integer.parseInt(token));
                    }
                }
            }
        }
        // Validate duplicates
        Set<Integer> set = new HashSet<>(values);
        if (set.size() != values.size()) {
            throw new IllegalArgumentException("Duplicate values detected.");
        }
        // Validate completeness (2^h - 1)
        int n = values.size();
        if (Integer.bitCount(n + 1) != 1) {
            throw new IllegalArgumentException(
                    "Node count " + n + " invalid: must be (2^h - 1) for a complete tree.");
        }
        int[] array = values.stream().mapToInt(i -> i).toArray();
        return new TreeState(array);
    }
}
