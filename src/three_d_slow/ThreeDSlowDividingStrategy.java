package three_d_slow;

import interfaces.DividingStrategy;
import mainComponents.DividingValuesHelper;
import mainComponents.Point;

import java.util.ArrayList;
import java.util.List;

public class ThreeDSlowDividingStrategy implements DividingStrategy {

    @Override
    public DividingValuesHelper dividePoints(List<List<Point>> points, int medianIndex, String splitAxis) {
        List<Point> pointsByX = points.get(0);
        List<Point> pointsByY = points.get(1);
        List<Point> pointsByZ = points.get(2);

        int median = points.get(0).get(medianIndex).getIndex().get("x"); //get the given index of the medianpoint at the median point index.

        // Get the x set
        List<Point> pointsByX_Left = new ArrayList<>();
        List<Point> pointsByX_Right = new ArrayList<>();
        for (Point point : pointsByX) {
            if (point.getIndex().get("x") <= median) {
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

        // Get the z set
        List<Point> pointsByZ_Left = new ArrayList<>();
        List<Point> pointsByZ_Right = new ArrayList<>();
        for (Point point : pointsByZ) {
            if (point.getIndex().get("x") <= median) {
                pointsByZ_Left.add(point);
            } else {
                pointsByZ_Right.add(point);
            }
        }

        List<List<Point>> left = new ArrayList<>();
        left.add(pointsByX_Left);
        left.add(pointsByY_Left);
        left.add(pointsByZ_Left);


        List<List<Point>> right = new ArrayList<>();
        right.add(pointsByX_Right);
        right.add(pointsByY_Right);
        right.add(pointsByZ_Right);

        return new DividingValuesHelper(left, right);
    }
}
