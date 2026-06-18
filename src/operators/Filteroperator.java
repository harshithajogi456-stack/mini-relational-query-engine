package operators;
import storage.Row;
import java.util.ArrayList;
import java.util.List;
public class Filteroperator implements Operator {
    private Operator input;
    private String col;
    private String val;
    private int count;
    public Filteroperator(Operator input, String col, String val) {
        this.input = input;
        this.col = col;
        this.val = val;
        count = 0;
    }
    public List<Row> execute() {
        List<Row> result = new ArrayList<>();
        for(Row row:input.execute()) {
            if(row.getVal(col).equals(val)) {
                result.add(row);
            }
        }
        count = result.size();
        return result;
    }
    public int getCount() {
        return count;
    }
    public Operator getInput() {
        return input;
    }
    public String getCol() {
        return col;
    }
    public String getVal() {
        return val;
    }
}


