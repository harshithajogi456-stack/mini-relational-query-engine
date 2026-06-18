package storage;
import java.util.List;
public class Schema {
    private List<String> cols;
    public Schema(List<String> cols) {
        this.cols = cols;
    } 
    public List<String> getCols() {
        return cols;
    }
    @Override
    public String toString() {
        return cols.toString();
    }
}