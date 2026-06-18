package storage;
import java.util.ArrayList;
import java.util.List;
public class Pagestore {
    private List<Page> pages;
    public Pagestore() {
        pages = new ArrayList<>();
    }
    public void addPage(Page page) {
        pages.add(page);
    }
    public List<Page> getPages() {
        return pages;
    }
}