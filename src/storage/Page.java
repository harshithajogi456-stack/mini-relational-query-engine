package storage;
import java.util.List;
public class Page {
    private List<Row> rows;
    public Page (List<Row> rows) {
        this.rows = rows;
    }
    public List<Row> getRows() {
        return rows;
    }
}