package scheduler;
import java.util.*;
public class Fcfs implements Schedulerpolicy {
    public void schedule(List<Queryjob> jobs) {
        System.out.println("\nFCFS Scheduling");
        int currTime = 0;
        int totalWait = 0;
        int totalTat = 0;

        for(Queryjob job:jobs) {
            int waitTime = currTime;
            currTime += job.getExecTime();
            int tatTime = currTime;
            totalWait += waitTime;
            totalTat += tatTime;

            System.out.println(job.getName() + " waiting time: " + waitTime + " ms, turnaround time: " + tatTime + " ms");
        }
        System.out.println("Average waiting time: " + (totalWait/jobs.size()) + " ms");
        System.out.println("Average turnaround time: " + (totalTat/jobs.size()) + " ms");
    }
}