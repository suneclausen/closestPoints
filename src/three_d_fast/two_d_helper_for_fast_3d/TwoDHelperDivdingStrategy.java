package three_d_fast.two_d_helper_for_fast_3d;

import interfaces.DividingStrategy;
import mainComponents.Point;

import java.util.ArrayList;
import java.util.List;

public class TwoDHelperDivdingStrategy implements DividingStrategy {
    @Override
    public List[] dividePoints(List<List<Point>> points, int medianIndex, String splitAxis) {
        // Constants
        int X = 0;
        int Y = 1;
        List<Point> pointsSortedByX = points.get(X);
        List<Point> pointsSortedByY = points.get(Y);
        // Return lists
        List<Point> x_left  = new ArrayList<>();
        List<Point> x_right = new ArrayList<>();
        List<Point> y_left  = new ArrayList<>();
        List<Point> y_right = new ArrayList<>();

        if (splitAxis.equals("x")){
            // Sorted x is the y coordinate of the points
            // Sorted y is the z coordinate of the points
            int median = pointsSortedByX.get(medianIndex).getIndex().get("y"); //get the given index of the medianpoint at the median point index.
            medianIndex = median;

            for (Point point : pointsSortedByX) {
                Integer xIndex = point.getIndex().get("y");
                if (xIndex <= medianIndex){
                    x_left.add(point);
                }else {
                    x_right.add(point);
                }
            }

            for (Point point : pointsSortedByY) {
                Integer xIndex = point.getIndex().get("y");
                if (xIndex <= medianIndex){
                    y_left.add(point);
                }else {
                    y_right.add(point);
                }
            }
        }
        if (splitAxis.equals("y") || splitAxis.equals("z")){
            /*
            if splitAxis == y
                # Sorted x is the x coordinate of the points
                # Sorted y is the z coordinate of the points

            if splitAxis == z
                # Sorted x is the x coordinate of the points
                # Sorted y is the y coordinate of the points
             */
            //TODO: Write better
            int median = points.get(0).get(medianIndex).getIndex().get("x"); //get the given index of the medianpoint at the median point index.
            medianIndex = median;

            for (Point point : pointsSortedByX) {
                Integer xIndex = point.getIndex().get("x");
                if (xIndex <= medianIndex){
                    x_left.add(point);
                }else {
                    x_right.add(point);
                }
            }

            for (Point point : pointsSortedByY) {
                Integer xIndex = point.getIndex().get("x");
                if (xIndex <= medianIndex){
                    x_left.add(point);
                }else {
                    x_right.add(point);
                }
            }
        }


        List<List<Point>> left = new ArrayList<>();
        List<List<Point>> right = new ArrayList<>();

        left.add(x_left);
        left.add(y_left);

        right.add(x_right);
        right.add(y_right);

        return new List[]{left, right};
    }
}
