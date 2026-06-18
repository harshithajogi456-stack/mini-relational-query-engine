package operators;
import storage.Row;
import java.util.List;
public interface Operator {
    List<Row> execute();
    int getCount();
}