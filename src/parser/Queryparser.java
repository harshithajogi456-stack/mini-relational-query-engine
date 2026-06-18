package parser;
import query.Predicate;
import query.Query;
import java.util.*;
public class Queryparser { 
    public Query parse(String input) {
        input = input.trim();
        if(input.isEmpty()) {
            throw new RuntimeException("Query is empty");
        }

        String[] parts = input.split(" ");
        if(parts.length<4) {
            throw new RuntimeException("Invalid query");
        }
        if(!parts[0].equalsIgnoreCase("select")) {
            throw new RuntimeException("Query must start with select");
        }
        if(!parts[2].equalsIgnoreCase("from")) {
            throw new RuntimeException("from is missing");
        }

        String colPart = parts[1];
        List<String> cols = new ArrayList<>();
        if(colPart.equals("*")) {
            cols.add("*");
        }
        else {
            String[] colArray = colPart.split(",");
            cols = Arrays.asList(colArray);
        }

        String table1 = parts[3];
        Predicate pred = null;
        String table2 = null;
        String joinLeft = null;
        String joinRight = null;
        if(table1.contains(",")) {
            String[] tables = table1.split(",");
            table1 = tables[0];
            table2 = tables[1];
            if(parts.length<6) {
                throw new RuntimeException("Invalid join query");
            }
            String condition = parts[5];
            String[] joinParts = condition.split("=");
            if(joinParts.length != 2) {
                throw new RuntimeException("Invalid join condition");
            }
            joinLeft = joinParts[0].substring(joinParts[0].indexOf(".")+1);
            joinRight = joinParts[1].substring(joinParts[1].indexOf(".")+1);
            return new Query(table1, table2, cols, null, null, joinLeft, joinRight);
        }
        if(parts.length>4) {
            if(!parts[4].equalsIgnoreCase("where")) {
                throw new RuntimeException("Invalid where condition");
            }

            String condition = parts[5];
            String[] conditionParts = condition.split("=");
            if(conditionParts.length != 2) {
                throw new RuntimeException("Invalid condition");
            }

            pred = new Predicate(conditionParts[0],conditionParts[1]);
        }

        // return new Query(table,Arrays.asList(colPart),pred,null);
        return new Query(table1,cols,pred,null);

    }
}