package buffer;
import java.util.*;
public class Buffermanager {
    private int capacity;
    private LinkedHashMap<String,String> cache;
    private int hits = 0;
    private int misses = 0;
    public Buffermanager (int capacity) {
        this.capacity = capacity;
        this.cache = new LinkedHashMap<>(capacity,0.75f,true) {
            protected boolean removeEldestEntry(Map.Entry<String,String> eldest) {
                return size()>Buffermanager.this.capacity;
            }
        };
    }
    public String get(String key) {
        if(cache.containsKey(key)) {
            hits++;
            return cache.get(key);
        }
        misses++;
        return null;
    }
    public void put(String key, String val) {
        cache.put(key,val);
    }
    public void printStats() {
        System.out.println("Hits: " + hits);
        System.out.println("Misses: " + misses);
    }
    public void resetStats() {
        hits = 0;
        misses = 0;
    }
}