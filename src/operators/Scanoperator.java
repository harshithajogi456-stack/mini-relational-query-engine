package operators;
import storage.Row;
import storage.Table;
import buffer.Buffermanager;
import java.util.*;
public class Scanoperator implements Operator {
    private Table table;
    private Buffermanager buffer;
    private int count;
    public Scanoperator(Table table, Buffermanager buffer) {
         this.table = table;
         this.buffer = buffer;
         this.count = 0;
    }
    public List<Row> execute() {
        List<Row> rows = new ArrayList<>();
        for(Row row:table.getRows()) {
            String key = table.getName()+row.toString();
            String cached = buffer.get(key);
            if(cached==null) {
                buffer.put(key, row.toString());
            }
            rows.add(row);
        }
        count = rows.size();
        return rows;
    }
    public int getCount() {
        return count;
    }
    public Table getTable() {
        return table;
    }
}
