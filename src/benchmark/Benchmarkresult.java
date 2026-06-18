package benchmark;
public class Benchmarkresult {
    private String query;
    private long time;
    private int rows;
    public Benchmarkresult (String query, long time, int rows) {
        this.query = query;
        this.time = time;
        this.rows = rows;
    }
    public String getQuery() {
        return query;
    }
    public long getTime() {
        return time;
    }
    public int getRows() {
        return rows;
    }
}