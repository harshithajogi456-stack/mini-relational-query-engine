package storage;
import buffer.*;
import java.util.*;
public class Table {
    private String name;
    private Schema schema;
    private List<Row> rows;
    private Map<String,Hashindex> indexes;
    private Buffermanager buffer;
    public Table (String name, Schema schema) {
        this.name = name;
        this.schema = schema;
        this.rows = new ArrayList<>();
        this.buffer = new Buffermanager(5);
        this.indexes = new HashMap<>();
    }
    public void addRow(Row row) {
        rows.add(row);
        for(Hashindex index:indexes.values()) {
            index.add(row);
        }
    }
    public List<Row> getRows() {
        for(int i=0;i<rows.size();i++) {
            String key = String.valueOf(i);
            if(buffer.get(key)==null) {
                buffer.put(key,rows.get(i).toString());
            }
        }
        return rows;
    }
    public Schema getSchema() {
        return schema;
    }
    public String getName() {
        return name;
    }
    public void createIndex(String col) {
        Hashindex index = new Hashindex(col);
        for(Row row:rows) {
            index.add(row);
        }
        indexes.put(col,index);
    }
    public List<Row> search(String col, String val) {
        Hashindex index = indexes.get(col);
        List<Row> result = index.search(val);
        for(Row row:result) {
            int pos = rows.indexOf(row);
            String key = String.valueOf(pos);
            if(buffer.get(key)==null) {
                buffer.put(key,row.toString());
            }
        }
        return result;
    }
    public boolean hasIndex(String col) {
        return indexes.containsKey(col);
    }
    public Buffermanager getBuffer() {
        return buffer;
    }
}