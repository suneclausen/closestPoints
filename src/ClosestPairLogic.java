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
    public ClosestPair closestPair(List<List<Point>> points) {
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

        int medianIndex = (int) (Math.ceil(numberOfPoints/2) - 1);
        // divide points and call recursively getting a side A and B
        List[] lists = dividePoints(points, points.get(0).get(medianIndex).getIndex());  //get the given index of the medianpoint at the median point index.
        List<List<Point>> left = lists[0];
        List<List<Point>> right = lists[1];
        ClosestPair closestPairLeft = closestPair(left);
        ClosestPair closestPairRight = closestPair(right);


        // Find which one is the minimum of A and B and set this as the closest pair seen so far
        ClosestPair currentClosestPair = setCurrentClosestPair(closestPairLeft, closestPairRight);

        // Build the slab
        double delta = currentClosestPair.getDistanceBetweenPoints();
        List<Point> slab = new ArrayList<>();
        List<Point> pointsSortedByY = points.get(1);
        for (Point point : pointsSortedByY) {
            int pointIndex = point.getIndex();
            if (pointIndex >= (medianIndex - delta) && pointIndex <= (medianIndex + delta)){
                slab.add(point);
            }
        }

        int numberOfSlabPoints = slab.size();

        // Traverse the slab
        for (int i = 0; i <numberOfSlabPoints; i++){
            Point currentPoint = slab.get(i);
            List<Point> pointsToConsider = getPointsToConsider(slab, sparsityConstant, i);

            for (Point point : pointsToConsider) {
                double distance = Utility.distance(currentPoint, point);
                if(distance < currentClosestPair.getDistanceBetweenPoints()){
                    currentClosestPair = new ClosestPair(currentPoint, point);
                }
            }
        }

        //return the currentClosestPoint
        return currentClosestPair;
    }


    private List<Point> getPointsToConsider(List<Point> slab, int sparsityConstant, int index) {
        ArrayList<Point> returnList = new ArrayList<>();
        int counter = 0;
        for (int i = index; i < slab.size(); i++){
            returnList.add(slab.get(i));
            counter++;
            if (counter == sparsityConstant){
                break;
            }
        }
        return returnList;
    }

    private ClosestPair setCurrentClosestPair(ClosestPair closestPairLeft, ClosestPair closestPairRight) {
        if (closestPairLeft.getDistanceBetweenPoints() < closestPairRight.getDistanceBetweenPoints()){
            return closestPairLeft;
        }else{
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
            if (point.getIndex() <= medianIndex){
                pointsByX_Left.add(point);
            }else{
                pointsByX_Right.add(point);
            }
        }

        // Get the y set
        List<Point> pointsByY_Left = new ArrayList<>();
        List<Point> pointsByY_Right = new ArrayList<>();
        for (Point point : pointsByY) {
            if (point.getIndex() <= medianIndex){
                pointsByY_Left.add(point);
            }else{
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

}
