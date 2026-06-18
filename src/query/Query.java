package query;
import java.util.List;
public class Query {
    private String name;
    private String name2;
    private List<String> cols;
    private Predicate pred;
    private Orderby order;
    private String joinLeft;
    private String joinRight;
    public Query(String name, List<String> cols, Predicate pred, Orderby order) {
        this.name = name;
        this.cols = cols;
        this.pred = pred;
        this.order = order;
    }
    public Query(String name, String name2, List<String> cols, Predicate pred, Orderby order, String joinLeft, String joinRight) {
        this.name = name;
        this.name2 = name2;
        this.cols = cols;
        this.pred = pred;
        this.order = order;
        this.joinLeft = joinLeft;
        this.joinRight = joinRight;
    }
    public String getName() {
        return name;
    }
    public String getName2() {
        return name2;
    }
    public List<String> getCols() {
        return cols;
    }
    public Predicate getPred() {
        return pred;
    }
    public Orderby getOrder() {
        return order;
    }
    public String getJoinLeft() {
        return joinLeft;
    }
    public String getJoinRight() {
        return joinRight;
    }
    public boolean hasJoin() {
        return name2 != null;
    }
}
