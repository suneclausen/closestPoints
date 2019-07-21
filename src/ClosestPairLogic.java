import java.security.cert.CollectionCertStoreParameters;
import java.util.*;

public class ClosestPairLogic {

    private int sparsityConstant = 12;

    public void setSparsityConstant(int sparsityConstant) {
        this.sparsityConstant = sparsityConstant;
    }

    public int getSparsityConstant() {
        return sparsityConstant;
    }

    //Points: Sorted set of points to the respectve coordinate axes
    public ClosestPair closestPair(List<List<Point>> points, String recursion) {

        checkSorting(points);

        int numberOfPoints = points.get(0).size();

        //stop condition
        if (numberOfPoints <= 3) {
            Point p1 = points.get(0).get(0);
            Point p2 = points.get(0).get(1);
            if (numberOfPoints == 2) {
                return new ClosestPair(p1, p2);
            }

            //return the minimum distance between the three points there is and the pair which makes this.
            Point p3 = points.get(0).get(2);

            ClosestPair[] possibilities = new ClosestPair[]{
                    new ClosestPair(p1, p2),
                    new ClosestPair(p1, p3),
                    new ClosestPair(p2, p3)
            };

            // Lambda programming for finding the pair which forms the closest distance between each other
            ClosestPair closestPairOfTheThreePossibilities = Arrays.asList(possibilities)
                    .stream()
                    .min(Comparator.comparing(ClosestPair::getDistanceBetweenPoints))
                    .orElseThrow(NoSuchElementException::new);

            return closestPairOfTheThreePossibilities;
        }

        int medianIndex = (int) (Math.ceil(((double) numberOfPoints / 2)) - 1);
        // divide points and call recursively getting a side A and B
        List[] lists = dividePoints(points, points.get(0).get(medianIndex).getIndex());  //get the given index of the medianpoint at the median point index.
        List<List<Point>> left = lists[0];
        List<List<Point>> right = lists[1];
        ClosestPair closestPairLeft = closestPair(left, recursion+"A");
        ClosestPair closestPairRight = closestPair(right, recursion+"B");


        // Find which one is the minimum of A and B and set this as the closest pair seen so far
        ClosestPair currentClosestPair = setCurrentClosestPair(closestPairLeft, closestPairRight);

        // Build the slab
        double delta = currentClosestPair.getDistanceBetweenPoints();

        List<Point> slab = new ArrayList<>();
        List<Point> pointsSortedByY = points.get(1);
        double medianPointX = points.get(0).get(medianIndex).getCoordinates()[0];
        for (int i = 0; i < pointsSortedByY.size(); i++) {
            Point point = pointsSortedByY.get(i);
            double currentPointX = point.getCoordinates()[0];
            if (currentPointX >= (medianPointX - delta) && currentPointX <= (medianPointX + delta)) {
                slab.add(point);
            }
        }

        int numberOfSlabPoints = slab.size();

        // Traverse the slab
        for (int i = 0; i < numberOfSlabPoints; i++) {
            Point currentPoint = slab.get(i);
            List<Point> pointsToConsider = getPointsToConsider(slab, sparsityConstant+1, i);

            for (Point point : pointsToConsider) {
                double distance = Utility.distance(currentPoint, point);
                if (distance < currentClosestPair.getDistanceBetweenPoints()) {
//                    System.out.println("wrote new closest pair from slab traversel with points: " + currentPoint + point);
                    currentClosestPair = new ClosestPair(currentPoint, point);
                }
            }
        }

        //return the currentClosestPoint
        return currentClosestPair;
    }

    private void checkSorting(List<List<Point>> points) {
        // index 0 = x
        // index 1 = y
        // for x
        List<Point> pointsSOrtedX = points.get(0);
        List<Point> pointsSOrtedY = points.get(1);

        assert pointsSOrtedX.size() == pointsSOrtedY.size();

        for (int i = 1; i <pointsSOrtedX.size() ; i++) {
            Point previousPoint = pointsSOrtedX.get(i-1);
            Point currentPoint = pointsSOrtedX.get(i);

            if (previousPoint.getCoordinates()[0] <= currentPoint.getCoordinates()[0]){
                continue;
            }else{
                throw new RuntimeException("The set was not sorted");
            }
        }
        for (int i = 1; i <pointsSOrtedY.size() ; i++) {
            Point previousPoint = pointsSOrtedY.get(i-1);
            Point currentPoint = pointsSOrtedY.get(i);

            if (previousPoint.getCoordinates()[1] <= currentPoint.getCoordinates()[1]){
                continue;
            }else{
                throw new RuntimeException("The set was not sorted");
            }
        }
    }


    private List<Point> getPointsToConsider(List<Point> slab, int sparsityConstant, int index) {
        ArrayList<Point> returnList = new ArrayList<>();
        int counter = 0;
        for (int i = index + 1; i < slab.size(); i++) {
            returnList.add(slab.get(i));
            if (counter == sparsityConstant || i == slab.size()-1) {
                break;
            }
            counter++;
        }
        return returnList;
    }

    private ClosestPair setCurrentClosestPair(ClosestPair closestPairLeft, ClosestPair closestPairRight) {
        if (closestPairLeft.getDistanceBetweenPoints() < closestPairRight.getDistanceBetweenPoints()) {
            return closestPairLeft;
        } else {
            return closestPairRight;
        }
    }

    public List[] dividePoints(List<List<Point>> points, int medianIndex) {
        List<Point> pointsByX = points.get(0);
        List<Point> pointsByY = points.get(1);

        // Get the x set
        List<Point> pointsByX_Left = new ArrayList<>();
        List<Point> pointsByX_Right = new ArrayList<>();
        for (Point point : pointsByX) {
            if (point.getIndex() <= medianIndex) {
                pointsByX_Left.add(point);
            } else {
                pointsByX_Right.add(point);
            }
        }

        // Get the y set
        List<Point> pointsByY_Left = new ArrayList<>();
        List<Point> pointsByY_Right = new ArrayList<>();
        for (Point point : pointsByY) {
            if (point.getIndex() <= medianIndex) {
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

        return new List[]{left, rigth};
    }

    // For now it is only for 2d
    public List<List<Point>> presort(List<Point> points) {
        int X = 0;
        int Y = 1;
        List<List<Point>> returnList = new ArrayList<>();

        //Sort by x
        List<Point> sortedByX = points;
        sortedByX.sort(new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                double value = (p1.getCoordinates()[X] - p2.getCoordinates()[X]);
                if (value < 0) {
                    return -1;
                } else if (value > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        // set the index of the point of where it is in the x-sorted-list
        for (int i = 0; i < sortedByX.size(); i++) {
            Point p = sortedByX.get(i);
            p.setIndex(i);
        }

        // Sort by y and keep the index
        List<Point> sortedByY = new ArrayList<>();
        sortedByY.addAll(sortedByX);
        Collections.sort(sortedByY, new Comparator<Point>() {
            @Override
            public int compare(Point p1, Point p2) {
                double value = (p1.getCoordinates()[Y] - p2.getCoordinates()[Y]);
                if (value < 0) {
                    return -1;
                } else if (value > 0) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
//        System.out.println("sorted y\n " + sortedByY);

        returnList.add(sortedByX);
        returnList.add(sortedByY);

        return returnList;
    }
}
