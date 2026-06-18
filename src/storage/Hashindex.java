package storage;
import java.util.*;
public class Hashindex {
    private String col;
    private Map<String,List<Row>> index;
    public Hashindex(String col) {
        this.col = col;
        index = new HashMap<>();
    }
    public void add(Row row) {
        String val = row.getVal(col);
        if(!index.containsKey(val)) {
            index.put(val,new ArrayList<>());
        }
        index.get(val).add(row);
    }
    public List<Row> search(String val) {
        if(index.containsKey(val)) {
            return index.get(val);
        }
        return new ArrayList<>();
    }
}