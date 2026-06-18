package main;

/* import storage.*;
public class Main {
    public static void main(String[] args) {
        try {
            Table students = Csvloader.load("students","data/students.csv");
            System.out.println("Table: " + students.getName());
            System.out.println("Columns: " + students.getSchema());
            System.out.println("\nrows:");
            for(Row row:students.getRows()) {
                System.out.println(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
} */

/* import query.Orderby;
import query.Predicate;
import query.Query;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        Query query = new Query ("students",Arrays.asList("name","marks"), new Predicate("marks","90"), new Orderby("name"));
        System.out.println(query.getName());
        System.out.println(query.getCols());
        System.out.println(query.getPred().getCol());
        System.out.println(query.getPred().getVal());
        System.out.println(query.getOrder().getCol());
    }
} */

/* import operators.Filteroperator;
import operators.Projectoperator;
import operators.Scanoperator;
import operators.Sortoperator;
import storage.Row;
import storage.Schema;
import storage.Table;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        List<String> cols = Arrays.asList("name", "marks");
        Schema schema = new Schema(cols); 
        Map<String,String> data1 = new HashMap<>();
        data1.put("name","Asha");
        data1.put("marks","90");
        Map<String,String> data2 = new HashMap<>();
        data2.put("name","Rahul");
        data2.put("marks","70");
        Row row1 = new Row(data1);
        Row row2 = new Row(data2);
        Table table = new Table("students",schema);
        table.addRow(row1);
        table.addRow(row2);
        Scanoperator scan = new Scanoperator(table);
        Filteroperator filter = new Filteroperator(scan, "marks", "90");
        Sortoperator sort = new Sortoperator(filter, "name");
        Projectoperator project = new Projectoperator(sort, Arrays.asList("name"));
        List<Row> result = project.execute();
        for(Row row:result){
            System.out.println(row.getVal("name"));
        }
        System.out.println("Rows scanned: " + scan.getCount());
        System.out.println("Rows after filter: " + filter.getCount());
        System.out.println("Rows after sort: " + sort.getCount());
    }
} */

/* import operators.Joinoperator;
import operators.Scanoperator;
import storage.Row;
import storage.Schema;
import storage.Table;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        Schema studSchema = new Schema(Arrays.asList("id","name"));
        Schema markSchema = new Schema(Arrays.asList("studentId","marks"));
        Table studs = new Table("students",studSchema);
        Map<String,String> stud1 = new HashMap<>();
        stud1.put("id", "1");
        stud1.put("name", "Asha");
        Map<String,String> stud2 = new HashMap<>();
        stud2.put("id", "2");
        stud2.put("name", "Rahul");
        studs.addRow(new Row(stud1));
        studs.addRow(new Row(stud2));
        Table marks = new Table("marks",markSchema);
        Map<String,String> mark1 = new HashMap<>();
        mark1.put("studentId", "1");
        mark1.put("marks", "90");
        Map<String,String> mark2 = new HashMap<>();
        mark2.put("studentId", "2");
        mark2.put("marks", "70");
        marks.addRow(new Row(mark1));
        marks.addRow(new Row(mark2));
        Scanoperator studScan = new Scanoperator(studs);
        Scanoperator marksScan = new Scanoperator(marks);
        Joinoperator join = new Joinoperator(studScan,marksScan,"id","studentId");
        List<Row> result = join.execute();
        for(Row row : result){
            System.out.println(row);
        }
        System.out.println("Joined rows: " + join.getCount());
    }

} */

import planner.*;
import query.Query;
import storage.*;
import java.util.*;
import optimizer.Optimizer;
import parser.Queryparser;
import scheduler.*;
import benchmark.*;
import buffer.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Benchmarkresult> benchmarkResults = new ArrayList<>();
        Buffermanager buffer = new Buffermanager(100);
        Map<String,Table> tables = new HashMap<>();
        while(true) {
            System.out.println("--------------------------------------------------\nMini Relational Query Engine\n\nEnter query/scheduler/exit:");
            String input = scanner.nextLine();
            if(input.equalsIgnoreCase("exit")) {
                break;
            }
            if(input.equalsIgnoreCase("scheduler")) {
                runScheduler();
                continue;
            }
            Query query;
            try {
                Queryparser parser = new Queryparser();
                query = parser.parse(input);
            }
            catch (Exception e) {
                System.out.println("Invalid query");
                continue;
            }
            try {
                Planner planner = new Planner();
                Execplan plan;              
                Table table = null;
                Table table1 = null;
                Table table2 = null;
                if(query.hasJoin()) {
                    if(!tables.containsKey(query.getName())) {
                        tables.put(query.getName(),Csvloader.load(query.getName(),"data/" + query.getName() + ".csv"));
                    }
                    if(!tables.containsKey(query.getName2())) {
                        tables.put(query.getName2(),Csvloader.load(query.getName2(),"data/" + query.getName2() + ".csv"));
                    }
                    table1 = tables.get(query.getName());
                    table2 = tables.get(query.getName2());
                    plan = planner.createJoinPlan(query,table1,table2, buffer);
                }
                else {
                    if(!tables.containsKey(query.getName())) {
                        tables.put(query.getName(),Csvloader.load(query.getName(),"data/" + query.getName() + ".csv"));
                    }
                    table = tables.get(query.getName()); 
                    plan = planner.createPlan(query,table,buffer);
                }
                Optimizer optimizer = new Optimizer();
                plan = optimizer.optimize(plan);
                buffer.resetStats();
                System.out.println("\nQuery Plan:");
                Explain.printPlan(plan.getRoot());

                long start = System.nanoTime();
                List<Row> result = plan.getRoot().execute();
                long end = System.nanoTime();
                System.out.println("\nQuery Result:");
                for(Row row:result) {
                    System.out.println(row);
                }
                long runtime = end - start;
                plan.getStats().setRowsProcessed(result.size());
                plan.getStats().setExecTime(runtime/1000000);
            
                System.out.println("\nExecution Statistics:");
                plan.getStats().printStats();

                System.out.println("\nBuffer Statistics:");
                if(table!=null) {
                    buffer.printStats();
                }
                if(table1!=null) {
                    System.out.println("\n" + query.getName() + ": ");
                    buffer.printStats();
                }
                if(table2!=null) {
                    System.out.println("\n" + query.getName2() + ": ");
                    buffer.printStats();
                }
                benchmarkResults.add(new Benchmarkresult(input,runtime,result.size()));
            }
            catch(Exception e) {
                System.out.println("Execution failed");
                e.printStackTrace();
            }
        }
        try {
            Benchmark.saveResults(benchmarkResults);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        scanner.close();
    }

    private static void runScheduler() {
        List<Queryjob> jobs = new ArrayList<>();
        jobs.add(new Queryjob("Query1",50,0));
        jobs.add(new Queryjob("Query2",20,1));
        jobs.add(new Queryjob("Query3",80,2));
        Schedulerpolicy fcfs = new Fcfs();
        fcfs.schedule(jobs);
        Schedulerpolicy sjf = new Sjf();
        sjf.schedule(jobs);
        Schedulerpolicy rr = new Roundrobin(10);
        rr.schedule(jobs);
    }
} 




