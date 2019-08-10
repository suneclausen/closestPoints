package mainComponents;

import java.util.ArrayList;
import java.util.List;

public class DividingValuesHelper {
    private List<List<Point>> left;
    private List<List<Point>> right;
    private String splitAxis;
    private List<Point> cutlist;

    public DividingValuesHelper(List<List<Point>> left, List<List<Point>> right) {
        this.left = left;
        this.right = right;
        this.splitAxis = "";
        this.cutlist = new ArrayList<>();
    }

    public List<List<Point>> getLeft() {
        return left;
    }

    public void setLeft(List<List<Point>> left) {
        this.left = left;
    }

    public List<List<Point>> getRight() {
        return right;
    }

    public void setRight(List<List<Point>> right) {
        this.right = right;
    }

    public String getSplitAxis() {
        return splitAxis;
    }

    public void setSplitAxis(String splitAxis) {
        this.splitAxis = splitAxis;
    }

    public List<Point> getCutlist() {
        return cutlist;
    }

    public void setCutlist(List<Point> cutlist) {
        this.cutlist = cutlist;
    }
}
