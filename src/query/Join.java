package query;
public class Join {
    private String leftCol;
    private String rightCol;
    public Join (String leftCol, String rightCol) {
        this.leftCol = leftCol;
        this.rightCol = rightCol;
    }
    public String getLeftCol() {
        return leftCol;
    }
    public String getRightCol() {
        return rightCol;
    }
}