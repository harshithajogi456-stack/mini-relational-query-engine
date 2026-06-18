package storage;
import java.util.*;
import java.io.*;
public class Csvloader {
    public static Table load(String name,String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(path));
        String header = reader.readLine();
        List<String> cols = new ArrayList<>();
        for(String col:header.split(",")) {
            cols.add(col.trim());
        }
        Schema schema = new Schema (cols);
        Table table = new Table(name, schema);
        String line;
        while((line = reader.readLine()) != null) {
            String[] values = line.split(",");
            Map <String,String> row = new LinkedHashMap<>();
            for(int i=0; i<cols.size(); i++) {
                row.put(cols.get(i), values[i].trim());
            }
            table.addRow(new Row(row));
        }
    reader.close();
    return table;
    } 
}