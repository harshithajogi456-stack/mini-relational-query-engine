package operators;
import storage.*;
import java.util.*;
public class Indexscanoperator implements Operator {
    private Table table;
    private String col;
    private String val;
    private int count;
    private String indexName;
    public Indexscanoperator(Table table, String col, String val) {
        this.table = table;
        this.col = col;
        this.val = val;
        this.count = 0;
        this.indexName = table.getIndexName(col);
    }
    public List<Row> execute() {
        List<Row> result = table.search(col,val);
        count = result.size();
        return result;
    }
    public int getCount() {
        return count;
    }
    public String getIndexName() {
        return indexName;
    }
}