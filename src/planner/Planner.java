package planner;
import operators.*;
import query.Query;
import storage.Table;
import buffer.Buffermanager;
public class Planner {
    public Execplan createPlan(Query query, Table table, Buffermanager buffer) {
        Operator operator;
        if(query.getPred()!=null) {
            if(table.hasIndex(query.getPred().getCol())) {
                operator = new Indexscanoperator(table,query.getPred().getCol(),query.getPred().getVal());
            }
            else {
                operator = new Scanoperator(table, buffer);
                operator = new Filteroperator(operator,query.getPred().getCol(),query.getPred().getVal());
            }
        }
        else {
            operator = new Scanoperator(table, buffer);
        }
        operator = new Projectoperator(operator,query.getCols());
        return new Execplan(operator);
    }
    public Execplan createJoinPlan(Query query, Table table1, Table table2, Buffermanager buffer) {
        Operator left = new Scanoperator(table1, buffer);
        Operator right = new Scanoperator(table2, buffer);
        Operator operator;
        if(table1.getRows().size() * table2.getRows().size() > 20) {
            System.out.println("Optimizer decison: ");
            System.out.println("Join choice: Hash join");
            System.out.println("Reason: Large tables");
            operator = new Hashjoinoperator(left,right,query.getJoinLeft(),query.getJoinRight());
        }
        else {
            System.out.println("Optimizer decison: ");
            System.out.println("Join choice: Nested loop join");
            System.out.println("Reason: Small tables");
            operator = new Joinoperator(left,right,query.getJoinLeft(),query.getJoinRight());
        }
        operator = new Projectoperator(operator,query.getCols());
        return new Execplan(operator);
    }
}
