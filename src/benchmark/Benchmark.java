package benchmark;
import java.util.*;
import java.io.*;
public class Benchmark {
    public static void saveResults(List<Benchmarkresult> results) throws IOException {
        FileWriter writer = new FileWriter("benchmark.csv",true);
        File file = new File("benchmark.csv");
        if(!file.exists()) {
            writer.write("Query,ExecutionTime,Rows\n");
        }
        for(Benchmarkresult r:results) {
            writer.write(r.getQuery() + "," + r.getTime() + "," + r.getRows() + "\n");
        }
        writer.close();
        System.out.println("\nBenchmark saved\n--------------------------------------------------\n");
    }
}