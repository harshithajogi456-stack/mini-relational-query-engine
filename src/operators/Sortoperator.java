package operators;
import storage.Row;
import java.util.*;
public class Sortoperator implements Operator {
    private Operator input;
    private String col;
    private int count;
    public Sortoperator (Operator input, String col) {
        this.input = input;
        this.col = col;
        this.count = 0;
    }
    public List<Row> execute() {
         List<Row> rows = new ArrayList<>(input.execute());
         rows.sort(Comparator.comparing(row -> row.getVal(col)));
         count = rows.size();
         return rows;
    }
    public int getCount() {
        return count;
    }
}