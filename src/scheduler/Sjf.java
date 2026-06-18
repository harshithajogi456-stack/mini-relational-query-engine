package scheduler;
import java.util.*;
public class Sjf implements Schedulerpolicy {
    public void schedule(List<Queryjob> jobs) {
        System.out.println("\nSJF Scheduling");
        jobs.sort(Comparator.comparingInt(Queryjob::getExecTime));
        for(Queryjob job:jobs) {
            System.out.println(job.getName() + " executed for " + job.getExecTime() + " ms");
        }
    }
}