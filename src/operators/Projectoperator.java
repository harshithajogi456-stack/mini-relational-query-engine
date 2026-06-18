package operators;
import storage.Row;
import java.util.*;
public class Projectoperator implements Operator {
    private Operator input;
    private List<String> cols;
    private int count;
    public Projectoperator (Operator input, List<String> cols) {
        this.input = input;
        this.cols = cols;
        this.count = 0;
    }
    public List<Row> execute() {
        List<Row> result = new ArrayList<>();
        for(Row row:input.execute()) {
            Map<String,String> data = new LinkedHashMap<>();
            if(cols.size()==1 && cols.get(0).equals("*")) {
                data.putAll(row.getVal());
            }
            else {
                for(String col:cols) {
                    data.put(col, row.getVal(col)); 
                }
            }
        result.add(new Row(data));
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
    public List<String> getCols() {
        return cols;
    }
}