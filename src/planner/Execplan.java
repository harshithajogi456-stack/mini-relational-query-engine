package planner;
import operators.Operator;
public class Execplan {
    private Operator root;
    private Execstats stats;
    public Execplan(Operator root) {
        this.root = root;
        this.stats = new Execstats();
    }
    public Operator getRoot() {
        return root;
    }
    public Execstats getStats() {
        return stats;
    }
}