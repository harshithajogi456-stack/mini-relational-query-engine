package operators;
import storage.Row;
import java.util.*;
public class Hashjoinoperator implements Operator {
    private Operator leftInput;
    private Operator rightInput;
    private String leftCol;
    private String rightCol;
    private int count;
    public Hashjoinoperator(Operator leftInput, Operator rightInput, String leftCol, String rightCol) { 
        this.leftInput = leftInput;
        this.rightInput = rightInput;
        this.leftCol = leftCol;
        this.rightCol = rightCol;
        count = 0;
    } 
    public List<Row> execute() {
        List<Row> result = new ArrayList<>();
        List<Row> leftRows = leftInput.execute();
        List<Row> rightRows = rightInput.execute();
        Map<String,Row> hashTable = new LinkedHashMap<>();
        for(Row row:rightRows) {
            hashTable.put(row.getVal(rightCol),row);
        }
        for(Row leftRow:leftRows) {
            Row rightRow = hashTable.get(leftRow.getVal(leftCol));
            if(rightRow != null) {
                Map<String,String> data = new LinkedHashMap<>();
                data.putAll(leftRow.getVal());
                data.putAll(rightRow.getVal());
                result.add(new Row(data));
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
}