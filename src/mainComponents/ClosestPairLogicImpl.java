package mainComponents;

import factories.ClosestPairFactory;
import interfaces.ClosestPairLogic;
import interfaces.DividingStrategy;
import interfaces.PresortStrategy;
import interfaces.SlabStrategy;
import three_d_fast.ThreeDFastDividingStrategy;


import java.lang.reflect.Array;
import java.util.*;

public class ClosestPairLogicImpl implements ClosestPairLogic {

    private final int dimension;
    private int sparsityConstant;

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


    private String debugSplit = "";
    private String splitAxis = "";

    public String getSplitAxis() {
        return splitAxis;
    }

    public void setSplitAxis(String splitAxis) {
        this.splitAxis = splitAxis;
    }

    //Param: Points: Sorted set of points to the respectve coordinate axes
    public ClosestPair closestPair(List<List<Point>> points, String recursion) {
        try {
            int numberOfPoints = points.get(0).size();

            if(recursion.equals("3D-Fast-TOP")){
                int debug = 2;
            }

            //stop condition
            if (numberOfPoints <= 3) {
                if (numberOfPoints == 0) {
                    int t = 2;
                    //DEBUG: TODO: DELETE
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
//        int medianIndexXValue = points.get(0).get(medianIndex).getIndex().get(0); //get the given index of the medianpoint at the median point index.

            List[] lists = dividePoints(points, medianIndex, splitAxis); //The splitAxis is only for when doing the fast implementation.
            List<List<Point>> left = lists[0];
            List<List<Point>> right = lists[1];
            ClosestPair closestPairLeft = closestPair(left, recursion + "A");
            ClosestPair closestPairRight = closestPair(right, recursion + "B");

            // Find which one is the minimum of A and B and set this as the closest pair seen so far
            ClosestPair currentClosestPair = setCurrentClosestPair(closestPairLeft, closestPairRight);

            // Build the slab
            double delta = currentClosestPair.getDistanceBetweenPoints();
            String splitAxis;
            List<Point> cutList;
            try {
                splitAxis = ((ThreeDFastDividingStrategy) divideStrat).getSplitAxis();
                debugSplit = splitAxis;
                cutList = ((ThreeDFastDividingStrategy) divideStrat).getCutList();
            } catch (Exception e) {
                splitAxis = "";
                cutList = new ArrayList<>();
            }
            List<List<Point>> slab = slabStrat.buildSlab(points, delta, medianIndex, splitAxis, cutList);

            // Traverse the slab - and compare the current closest pair and the potentiel closest pair in the slab and return this.
            currentClosestPair = slabStrat.traverseSlab(currentClosestPair, slab, this, splitAxis);

            //return the currentClosestPoint
            return currentClosestPair;
        } catch (Exception e) {
            System.out.println("Print the points in recursion " + recursion + " with choosen splitaxis: " + debugSplit + " or " + splitAxis + ". There are " + points.get(0).size() + " points. The points are: \n" + points.get(0));

            System.out.println(Arrays.toString(e.getStackTrace()));
        }
        return null;
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

    public int getSparsityConstant() {
        return sparsityConstant;
    }

    @Override
    public List[] dividePoints(List<List<Point>> points, int medianIndex, String splitAxis) {
        return divideStrat.dividePoints(points, medianIndex, splitAxis);
    }

    @Override
    public List<List<Point>> presort(List<Point> points) {
        return presortStrat.presort(points);
    }

    public int getDimension() {
        return dimension;
    }
}
