package mainComponents;

import factories.ClosestPairFactory;
import interfaces.ClosestPairLogic;
import interfaces.DividingStrategy;
import interfaces.PresortStrategy;
import interfaces.SlabStrategy;
import java.util.*;

public class ClosestPairLogicImpl implements ClosestPairLogic {

    private final int dimension;
    private int sparsityConstant;
    private String splitAxis = "";

    // Strategies
    private DividingStrategy divideStrat;
    private PresortStrategy presortStrat;
    private SlabStrategy slabStrat;

    public ClosestPairLogicImpl(int originalDimension, ClosestPairFactory stratFactory) {
        this.sparsityConstant = (int) (4 * (Math.pow(3, (originalDimension - 1))));  //From theory by Bentley and Shamos: sparsityConstant = 4(3^{d-1}) , d = dimension
        this.divideStrat = stratFactory.getDividePointsStrategy();
        this.presortStrat = stratFactory.getPresortStrategy();
        this.slabStrat = stratFactory.getSlabStrategy();

        this.dimension = originalDimension;
    }

    //Param: Points: Sorted set of points to the respective coordinate axes
    public ClosestPair closestPair(List<List<Point>> points, String recursion) {
        int numberOfPoints = points.get(0).size();

        //stop condition
        if (numberOfPoints <= 3) {
            if (numberOfPoints == 0) {
                // TODO; Figure out why it happens that we sometimes enter this part.
                Point bufferPoint = new Point(getDimension(), new double[]{0, 0, 0});
                ClosestPair infDist = new ClosestPair(bufferPoint, bufferPoint);
                infDist.setDistanceBetweenPoints(Double.POSITIVE_INFINITY);
                return infDist;
            }

            Point p1 = points.get(0).get(0);
            if (numberOfPoints == 1) {
                // When we only have 1 point we return a ClosestPair with a value so high that it will never be returned
                ClosestPair infDist = new ClosestPair(p1, p1);
                infDist.setDistanceBetweenPoints(Double.POSITIVE_INFINITY);
                return infDist;
            }

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

        // Find median index for the diving line which will split the set into two parts 'left' and 'right' of almost equal size.
        int medianIndex = (int) (Math.ceil(((double) numberOfPoints / 2)) - 1);

        // Divide points and call recursively getting a side A and B
        DividingValuesHelper divideHelper = dividePoints(points, medianIndex, splitAxis);//The splitAxis is only for when doing the fast implementation when it is projected down.
        List<List<Point>> left = divideHelper.getLeft();
        List<List<Point>> right = divideHelper.getRight();
        ClosestPair closestPairLeft = closestPair(left, recursion + "A");
        ClosestPair closestPairRight = closestPair(right, recursion + "B");

        // Find which one is the minimum of A and B and set this as the closest pair seen so far
        ClosestPair currentClosestPair = setCurrentClosestPair(closestPairLeft, closestPairRight);

        // Build the slab
        double delta = currentClosestPair.getDistanceBetweenPoints();
        List<List<Point>> slab = slabStrat.buildSlab(points, delta, medianIndex, divideHelper.getSplitAxis(), divideHelper.getCutlist());

        // Traverse the slab - and compare the current closest pair and the potentiel closest pair in the slab and return this.
        currentClosestPair = slabStrat.traverseSlab(currentClosestPair, slab, this, divideHelper.getSplitAxis());

        //return the currentClosestPoint
        return currentClosestPair;

    }

    // Find how many points to consider in a sorted order based on the sparsity constant and only considering the points from the slab.
    public List<Point> getPointsToConsider(List<Point> slab, int sparsityConstant, int index) {
        ArrayList<Point> returnList = new ArrayList<>();
        int counter = 0;
        for (int i = index + 1; i < slab.size(); i++) {
            returnList.add(slab.get(i));
            if (counter == sparsityConstant || i == slab.size() - 1) {
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

    @Override
    public DividingValuesHelper dividePoints(List<List<Point>> points, int medianIndex, String splitAxis) {
        return divideStrat.dividePoints(points, medianIndex, splitAxis);
    }

    @Override
    public List<List<Point>> presort(List<Point> points) {
        return presortStrat.presort(points);
    }

    public int getDimension() {
        return dimension;
    }

    public int getSparsityConstant() {
        return sparsityConstant;
    }

    public String getSplitAxis() {
        return splitAxis;
    }

    public void setSplitAxis(String splitAxis) {
        this.splitAxis = splitAxis;
    }
}
