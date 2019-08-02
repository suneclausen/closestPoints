package three_d_fast;

import interfaces.DividingStrategy;
import mainComponents.Point;

import java.util.ArrayList;
import java.util.List;

public class ThreeDFastDividingStrategy implements DividingStrategy {
    @Override
    public List[] dividePoints(List<List<Point>> points, int medianIndex) {
        // Constants
        int X = 0;
        int Y = 1;
        int Z = 2;
        List<Point> pointsSortedByX = points.get(X);
        List<Point> pointsSortedByY = points.get(Y);
        List<Point> pointsSortedByZ = points.get(Z);
        // Return lists
        List<Point> x_left  = new ArrayList<>();
        List<Point> x_right = new ArrayList<>();
        List<Point> y_left  = new ArrayList<>();
        List<Point> y_right = new ArrayList<>();
        List<Point> z_left  = new ArrayList<>();
        List<Point> z_right = new ArrayList<>();

        // Dimension - hardcoded
        double k = 3.0;

        // Sparsity constant for 3d. Could be gotten from logic, but for easiness chosen to be harcoded here
        double c = 36.0;

        // Number of points.
        double n = (double) points.get(0).size();

        // Points of interest.
        double rulerSize = k * c * Math.pow(n, (1-(1/k)));

        // We have to make sure that there is at least n/4k points on one of the sides.
        double pointsToLeaveOut = Math.ceil(n / (4 * k));

        // Variables to mutate
        double distance = 0;
        List<Point> lst = null;
        String splitAxis = null;
        
        // Go through each of the axis ans project all the points and find the set that gives the biggest beteen kcn^{1-1/k} points. 
        // For the x-axis
        for (int i = (int) pointsToLeaveOut; i < Math.ceil(n - pointsToLeaveOut) ; i++) {
            int end_index = (int) Math.min(i + rulerSize, n - pointsToLeaveOut - 1);
            double bufferDistance = pointsSortedByX.get(end_index).getCoordinates()[X] - pointsSortedByX.get(i).getCoordinates()[X] ;
            if (bufferDistance > distance){
                distance = bufferDistance;
                List<Point> cutList = new ArrayList<>();
                cutList.addAll(pointsSortedByX);
                lst = cutList.subList(i, end_index + 1);
                splitAxis = "x";
            }
        }

        // For the y-axis
        for (int i = (int) pointsToLeaveOut; i < Math.ceil(n - pointsToLeaveOut) ; i++) {
            int end_index = (int) Math.min(i + rulerSize, n - pointsToLeaveOut - 1);
            double bufferDistance = pointsSortedByY.get(end_index).getCoordinates()[Y] - pointsSortedByY.get(i).getCoordinates()[Y] ;
            if (bufferDistance > distance){
                distance = bufferDistance;
                List<Point> cutList = new ArrayList<>();
                cutList.addAll(pointsSortedByY);
                lst = cutList.subList(i, end_index + 1);
                splitAxis = "y";
            }
        }

        // For the z-axis
        for (int i = (int) pointsToLeaveOut; i < Math.ceil(n - pointsToLeaveOut) ; i++) {
            int end_index = (int) Math.min(i + rulerSize, n - pointsToLeaveOut - 1);
            double bufferDistance = pointsSortedByZ.get(end_index).getCoordinates()[Z] - pointsSortedByZ.get(i).getCoordinates()[Z] ;
            if (bufferDistance > distance){
                distance = bufferDistance;
                List<Point> cutList = new ArrayList<>();
                cutList.addAll(pointsSortedByZ);
                lst = cutList.subList(i, end_index + 1);
                splitAxis = "z";
            }
        }

        // We have now found the splitaxis and the kcn^{1-1/k} points within
        // Now we need to split the points accordingly to the split axis
        if (splitAxis.equals("x")){
            double medianValue = (lst.get(0).getCoordinates()[X] + lst.get(lst.size()-1).getCoordinates()[X]) / 2;
            medianIndex = 0;
            for (Point point : pointsSortedByX) {
                if (point.getCoordinates()[X] > medianValue){
                    medianIndex = point.getIndex().get(X);
                    break;
                }
            }

            for (Point point : pointsSortedByX) {
                int indexValue = point.getIndex().get(X);
                if (indexValue <= medianIndex){
                    x_left.add(point);
                }else{
                    x_right.add(point);
                }
            }
            for (Point point : pointsSortedByY) {
                int indexValue = point.getIndex().get(X);
                if (indexValue <= medianIndex){
                    y_left.add(point);
                }else{
                    y_right.add(point);
                }
            }
            for (Point point : pointsSortedByZ) {
                int indexValue = point.getIndex().get(X);
                if (indexValue <= medianIndex){
                    z_left.add(point);
                }else{
                    z_right.add(point);
                }
            }
        }

        if (splitAxis.equals("y")){
            double medianValue = (lst.get(0).getCoordinates()[Y] + lst.get(lst.size()-1).getCoordinates()[Y]) / 2;
            medianIndex = 0;
            for (Point point : pointsSortedByY) {
                if (point.getCoordinates()[Y] > medianValue){
                    medianIndex = point.getIndex().get(Y);
                    break;
                }
            }

            for (Point point : pointsSortedByX) {
                int indexValue = point.getIndex().get(Y);
                if (indexValue <= medianIndex){
                    x_left.add(point);
                }else{
                    x_right.add(point);
                }
            }
            for (Point point : pointsSortedByY) {
                int indexValue = point.getIndex().get(Y);
                if (indexValue <= medianIndex){
                    y_left.add(point);
                }else{
                    y_right.add(point);
                }
            }
            for (Point point : pointsSortedByZ) {
                int indexValue = point.getIndex().get(Y);
                if (indexValue <= medianIndex){
                    z_left.add(point);
                }else{
                    z_right.add(point);
                }
            }
        }

        if (splitAxis.equals("z")){
            double medianValue = (lst.get(0).getCoordinates()[Z] + lst.get(lst.size()-1).getCoordinates()[Z]) / 2;
            medianIndex = 0;
            for (Point point : pointsSortedByZ) {
                if (point.getCoordinates()[Z] > medianValue){
                    medianIndex = point.getIndex().get(Z);
                    break;
                }
            }

            for (Point point : pointsSortedByX) {
                int indexValue = point.getIndex().get(Z);
                if (indexValue <= medianIndex){
                    x_left.add(point);
                }else{
                    x_right.add(point);
                }
            }
            for (Point point : pointsSortedByZ) {
                int indexValue = point.getIndex().get(Z);
                if (indexValue <= medianIndex){
                    y_left.add(point);
                }else{
                    y_right.add(point);
                }
            }
            for (Point point : pointsSortedByZ) {
                int indexValue = point.getIndex().get(Z);
                if (indexValue <= medianIndex){
                    z_left.add(point);
                }else{
                    z_right.add(point);
                }
            }
        }


        List<List<Point>> left = new ArrayList<>();
        List<List<Point>> right = new ArrayList<>();

        left.add(x_left);
        left.add(y_left);
        left.add(z_left);

        right.add(x_right);
        right.add(y_right);
        right.add(z_right);

        return new List[]{left, right};
    }
}
