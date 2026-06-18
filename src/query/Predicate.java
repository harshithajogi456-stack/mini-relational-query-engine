package query;
public class Predicate {
     private String col;
     private String val;
     public Predicate(String col, String val) {
        this.col = col;
        this.val = val;
     }
     public String getCol() {
        return col;
     }
     public String getVal() {
        return val;
     }
}