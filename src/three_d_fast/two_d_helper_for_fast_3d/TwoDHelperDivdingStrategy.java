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

        //TODO: Write better
//        int medianIndexXValue = points.get(0).get(medianIndex).getIndex().get(0); //get the given index of the medianpoint at the median point index.
//        medianIndex = medianIndexXValue;

//        String splitAxis = "x"; // TODO: Get splitaxis somehow.

        //TODO: START
        int medianIndexXValue = points.get(0).get(medianIndex).getIndex().get(X); //get the given index of the medianpoint at the median point index.
        medianIndex = medianIndexXValue;

        for (Point point : pointsSortedByX) {
            Integer xIndex = point.getIndex().get(X);
            if (xIndex <= medianIndex){
                x_left.add(point);
            }else {
                x_right.add(point);
            }
        }

        for (Point point : pointsSortedByY) {
            Integer xIndex = point.getIndex().get(X);
            if (xIndex <= medianIndex){
                y_left.add(point);
            }else {
                y_right.add(point);
            }
        }
        // TODO: STOP

//        if (splitAxis.equals("x")){
//            //TODO: Write better
//            // TODO: Might not work. Shall perhaps be just index x since ths logic removes one index each time.
//            int medianIndexXValue = points.get(0).get(medianIndex).getIndex().get(X); //get the given index of the medianpoint at the median point index.
//            medianIndex = medianIndexXValue;
//
//            for (Point point : pointsSortedByX) {
//                Integer xIndex = point.getIndex().get(X);
//                if (xIndex <= medianIndex){
//                    x_left.add(point);
//                }else {
//                    x_right.add(point);
//                }
//            }
//
//            for (Point point : pointsSortedByY) {
//                Integer xIndex = point.getIndex().get(X);
//                if (xIndex <= medianIndex){
//                    y_left.add(point);
//                }else {
//                    y_right.add(point);
//                }
//            }
//        }
//        if (splitAxis.equals("y") || splitAxis.equals("z")){
//            //TODO: Write better
//            int medianIndexXValue = points.get(0).get(medianIndex).getIndex().get(X); //get the given index of the medianpoint at the median point index.
//            medianIndex = medianIndexXValue;
//
//            for (Point point : pointsSortedByX) {
//                Integer xIndex = point.getIndex().get(X);
//                if (xIndex <= medianIndex){
//                    x_left.add(point);
//                }else {
//                    x_right.add(point);
//                }
//            }
//
//            for (Point point : pointsSortedByY) {
//                Integer xIndex = point.getIndex().get(X);
//                if (xIndex <= medianIndex){
//                    x_left.add(point);
//                }else {
//                    x_right.add(point);
//                }
//            }
//
//        }
//        if (splitAxis.equals("z")){
//
//        }


        List<List<Point>> left = new ArrayList<>();
        List<List<Point>> right = new ArrayList<>();

        left.add(x_left);
        left.add(y_left);

        right.add(x_right);
        right.add(y_right);

        return new List[]{left, right};
    }
}
