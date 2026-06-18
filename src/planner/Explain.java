package planner;
import operators.*;
public class Explain {
    public static void printPlan(Operator operator) {
        print(operator,"");
    }
    private static void print(Operator operator, String indent) {
        System.out.println(indent + operator.getClass().getSimpleName());
        if(operator instanceof Projectoperator) {
            Projectoperator p = (Projectoperator) operator;
            print(p.getInput(),indent + "   ");
        }
        else if(operator instanceof Filteroperator) {
            Filteroperator f = (Filteroperator) operator;
            print(f.getInput(),indent + "   ");
        }
        else if(operator instanceof Indexscanoperator) {
            System.out.println(indent + "   Access: Hash index scan");
            System.out.println(indent + "   Reason: Predicate column has index");

        }
        else if(operator instanceof Scanoperator) {
            System.out.println(indent + "   Access: Table scan");
            System.out.println(indent + "   Reason: Full table read");
        }
        else if(operator instanceof Hashjoinoperator) {
            Hashjoinoperator h = (Hashjoinoperator)operator;
            print(h.getLeft(),indent+ "     ");
            print(h.getRight(),indent+ "    ");
        }
        else if(operator instanceof Joinoperator) {
            Joinoperator j = (Joinoperator) operator;
            print(j.getLeft(),indent + "    ");
            print(j.getRight(),indent+ "    ");
        }
    }

}