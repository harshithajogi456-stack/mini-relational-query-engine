package operators;
import storage.Row;
import java.util.*;
public class Joinoperator implements Operator {
    private Operator leftInput;
    private Operator rightInput;
    private String leftCol;
    private String rightCol;
    private int count;
    public Joinoperator (Operator leftInput, Operator rightInput, String leftCol, String rightCol) {
        this.leftInput = leftInput;
        this.rightInput = rightInput;
        this.leftCol = leftCol;
        this.rightCol = rightCol;
        this.count = 0;
    }
    public List<Row> execute() {
        List<Row> result = new ArrayList<>();
        List<Row> leftRows = leftInput.execute();
        List<Row> rightRows = rightInput.execute();
        for(Row leftRow:leftRows) {
            for(Row rightRow:rightRows) {
                if(leftRow.getVal(leftCol).equals(rightRow.getVal(rightCol))) {
                    Map<String,String> data = new LinkedHashMap<>();
                    data.putAll(leftRow.getVal());
                    data.putAll(rightRow.getVal());
                    result.add(new Row(data));
                }
            }
        }
        count = result.size();
        return result;
    }
    public int getCount() {
        return count;
    }
    public Operator getLeft() {
        return leftInput;
    }
    public Operator getRight() {
        return rightInput;
    }
    public String getLeftCol() {
        return leftCol;
    }
    public String getRightCol() {
        return rightCol;
    }
} 