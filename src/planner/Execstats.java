package planner;
public class Execstats {
    private int rowsProcessed;
    private long execTime;
    public Execstats() {
        rowsProcessed = 0;
        execTime = 0;
    }
    public void setRowsProcessed(int rows) {
        this.rowsProcessed = rows;
    }
    public void setExecTime(long time){
        this.execTime = time;
    }
    public int getrowsProcessed() {
        return rowsProcessed;
    }
    public long getExecTime() {
        return execTime;
    }
    public void printStats() {
        System.out.println("Rows processed: " + rowsProcessed);
        System.out.println("Execution time: " + execTime + " ms");
    }
}