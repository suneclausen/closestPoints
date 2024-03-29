package two_d;

import interfaces.DividingStrategy;
import mainComponents.DividingValuesHelper;
import mainComponents.Point;

import java.util.ArrayList;
import java.util.List;

public class TwoDDividingStrategy implements DividingStrategy {
    @Override
    public DividingValuesHelper dividePoints(List<List<Point>> points, int medianIndex, String splitAxis) {
        List<Point> pointsByX = points.get(0);
        List<Point> pointsByY = points.get(1);

        int median = points.get(0).get(medianIndex).getIndex().get("x"); //get the given index of the medianpoint at the median point index.

        // Get the x set
        List<Point> pointsByX_Left = new ArrayList<>();
        List<Point> pointsByX_Right = new ArrayList<>();
        for (Point point : pointsByX) {
            if (point.getIndex().get("x") <= median) { //get(0) gets the index of x
                pointsByX_Left.add(point);
            } else {
                pointsByX_Right.add(point);
            }
        }

        // Get the y set
        List<Point> pointsByY_Left = new ArrayList<>();
        List<Point> pointsByY_Right = new ArrayList<>();
        for (Point point : pointsByY) {
            if (point.getIndex().get("x") <= median) {
                pointsByY_Left.add(point);
            } else {
                pointsByY_Right.add(point);
            }
        }

        List<List<Point>> left = new ArrayList<>();
        List<List<Point>> rigth = new ArrayList<>();
        left.add(pointsByX_Left);
        left.add(pointsByY_Left);
        rigth.add(pointsByX_Right);
        rigth.add(pointsByY_Right);

        return new DividingValuesHelper(left, rigth);
    }
}
