package scheduler;
public class Queryjob {
    private String name;
    private int execTime;
    private int arrTime;
    public Queryjob(String name, int execTime, int arrTime) {
        this.name = name;
        this.execTime = execTime;
        this.arrTime = arrTime; 
    }
    public String getName() {
        return name;
    }
    public int getExecTime() {
        return execTime;
    }
    public int getArrTime() {
        return arrTime;
    }
}