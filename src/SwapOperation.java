/** Name - P.W.Achira Uwanpriya Senadheera
   IIT Id - 20232703
   UOW Id - w2082264
*/
public class SwapOperation {
    private final int index, parentIndex;

    public SwapOperation(int index, int parentIndex) {
        this.index = index;
        this.parentIndex = parentIndex;
    }

    public int getIndex() { return index; }
    public int getParentIndex() { return parentIndex; }

    public TreeState apply(TreeState state) { return state.apply(this); }

    @Override
    public String toString() {
        return "Swap index " + index + " with parent index " + parentIndex;
    }
}
