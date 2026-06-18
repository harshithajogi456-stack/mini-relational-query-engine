package scheduler;
import java.util.*;
public class Roundrobin implements Schedulerpolicy {
    private int quantum;
    public Roundrobin(int quantum) {
        this.quantum = quantum;
    }
    public void schedule(List<Queryjob> jobs) {
        System.out.println("\nRound Robin scheduling");
        Queue<Queryjob> queue = new LinkedList<>(jobs);
        Map<String,Integer> remaining = new HashMap<>();
        Map<String,Integer> finish = new HashMap<>();
        for(Queryjob job:jobs) {
            remaining.put(job.getName(),job.getExecTime());
        }
        int currTime = 0;
        while(!queue.isEmpty()) {
            Queryjob job = queue.poll();
            int remain = remaining.get(job.getName());
            int runTime = Math.min(quantum,remain);
            currTime += runTime;
            remain -= runTime;
            System.out.println(job.getName() + " running for " + runTime + " ms");
            if(remain>0) {
                remaining.put(job.getName(), remain);
                queue.add(job);
            }
            else {
                finish.put(job.getName(), currTime);
            }
        }
        int totalWait = 0;
        int totalTat = 0;
        for(Queryjob job:jobs) {
            int tat = finish.get(job.getName());
            int wait = tat - job.getExecTime();
            totalWait += wait;
            totalTat += tat;
        }
        System.out.println("Average waiting time: " + (totalWait/jobs.size()) + " ms");
        System.out.println("Average turnaround time: " + (totalTat/jobs.size()) + " ms");
    }
}