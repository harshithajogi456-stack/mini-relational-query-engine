package storage;
import java.util.*;
public class Row {
    private Map<String,String> value;
    public Row (Map<String,String> value) {
        this.value = new LinkedHashMap<>(value);
    }
    public String getVal(String col) {
        return value.get(col);
    }
    public Map<String,String> getVal() {
        return value;
    }
    public String toString() {
        return value.entrySet().toString();
    }
}