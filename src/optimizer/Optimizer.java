package optimizer;
import planner.Execplan;
import operators.*;
import storage.Table;
public class Optimizer {
    public Execplan optimize(Execplan plan) {
        Operator root = plan.getRoot();
        if(root instanceof Projectoperator) {
            Projectoperator project = (Projectoperator) root;
            Operator child = project.getInput();
            if(child instanceof Filteroperator) {
                Filteroperator filter = (Filteroperator) child;
                Operator input = filter.getInput();
                if(input instanceof Scanoperator) {
                    Scanoperator scan = (Scanoperator) input;
                    Table table = scan.getTable();
                    String col = filter.getCol();
                    if(!table.hasIndex(col)) {
                        System.out.println("\nAccess method: Table scan");
                        System.out.println("Reason: Creating index for future queries");
                        table.createIndex(col);
                        return plan;
                    }
                    else {
                        System.out.println("\nAccess method: Hash index scan");
                        System.out.println("reason: Predicate column has index");
                        Indexscanoperator indexScan = new Indexscanoperator(table,col,filter.getVal());
                        return new Execplan(new Projectoperator(indexScan,project.getCols()));
                    }
                }
            }
            if(child instanceof Joinoperator) {
                return plan;
            }
        }       
        return plan;
    }
}