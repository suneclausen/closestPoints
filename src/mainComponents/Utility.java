package mainComponents;

import interfaces.DividingStrategy;

import java.util.*;

public class Utility {

    //Calculate the euclidian distance
    public static double distance(Point point1, Point point2) {
        double[] coordinatesPoint1 = point1.getCoordinates();
        double[] coordinatesPoint2 = point2.getCoordinates();
        int dimension = point1.getDimension();
        assert coordinatesPoint1.length == coordinatesPoint2.length; //Assume points have same dimension

        double temp = 0.0;
        for (int i = 0; i < dimension; i++) {
            double coordinateValFromPoint1 = coordinatesPoint1[i];
            double coordinateValFromPoint2 = coordinatesPoint2[i];
            double diffValue = coordinateValFromPoint1 - coordinateValFromPoint2;
            temp += Math.pow(diffValue, 2);
        }

        return Math.sqrt(temp);
    }

    //TODO: Make more dynamic and generic
    public static List<Point> generateRandomPoints(int numberOfPoints, int upperBound, int dimension, boolean asInt) {
        Set<Point> points = new LinkedHashSet<>();

        while (points.size() < numberOfPoints) {
            double[] coordinate = new double[dimension];
            for (int j = 0; j < dimension; j++) {
                if (asInt) { //meaning wihh .000
                    int number = (int) (Math.random() * upperBound);
                    coordinate[j] = ((double) number);
                } else {
                    coordinate[j] = Math.random() * upperBound;
                }
            }
            Point p = new Point(dimension, coordinate);
//            System.out.println("new point added" + p +  " making the size of points to be " + points.size());
            points.add(p);
//            System.out.println("Now" + points.size());
        }

        // Convert the hashSet into a lsit to make logic work.
        List<Point> returnList = new ArrayList<>();
        returnList.addAll(points);

        return returnList;
    }


    public static ClosestPair bruteforce(List<Point> points) {
        ClosestPair cp = new ClosestPair(points.get(0), points.get(1));

        for (int i = 0; i < points.size(); i++) {
            Point currentPoint = points.get(i);
            for (int j = 0; j < points.size(); j++) {
                if (i == j){ // it is the same point
                    continue;
                }
                Point comparePoint = points.get(j);
                if (comparePoint.getCoordinates().equals(currentPoint.getCoordinates())){
                    // it is then the same point... Should not happen, since dataset will not then be sparse
                    continue;
                }
                double distance = distance(currentPoint, comparePoint);
                if (distance < cp.getDistanceBetweenPoints()) {
                    cp = new ClosestPair(currentPoint, comparePoint);
                }
            }
        }
        return cp;
    }

    public static List<List<Point>> verifyAlgorithm(int dimension, int iterations, int maxNumberOfPoints, int upperBoudnValueForPoint, DividingStrategy divStrat) {  //TODO: Make strat for 2d  into factories
        // for know hardcoded to support 2d
        System.out.println("Verify algorithm");
        ClosestPairLogicImpl logic = new ClosestPairLogicImpl(divStrat);

        for (int i = 0; i < iterations; i++) {
            progressPercentage(i+1, iterations);
            int numberOfPoints = (int) (Math.random() * maxNumberOfPoints) + 2;
            int upperBound = 1000; //for how big the numbers can be

            List<Point> points = generateRandomPoints(numberOfPoints, upperBoudnValueForPoint, dimension, true);

            ClosestPair bruteforce = bruteforce(points);
            double dist = bruteforce.getDistanceBetweenPoints();


            List<List<Point>> presort = logic.presort(points);
            ClosestPair closestPair = logic.closestPair(presort, "TOP");

            if (closestPair.getDistanceBetweenPoints() != dist) {
                System.out.println("------ ERROR ------");
                System.out.println("Bruteforce gave:" + bruteforce);
                System.out.println("ClosestPair gave:" + closestPair);
                return presort;
            }

        }
        return null;
    }

    public static void progressPercentage(int remain, int total) {
        if (remain > total) {
            throw new IllegalArgumentException();
        }
        int maxBareSize = 10; // 10unit for 100%
        int remainProcent = ((100 * remain) / total) / maxBareSize;
        char defaultChar = '-';
        String icon = "*";
        String bare = new String(new char[maxBareSize]).replace('\0', defaultChar) + "]";
        StringBuilder bareDone = new StringBuilder();
        bareDone.append("[");
        for (int i = 0; i < remainProcent; i++) {
            bareDone.append(icon);
        }
        String bareRemain = bare.substring(remainProcent, bare.length());
        System.out.print("\r" + bareDone + bareRemain + " " + remainProcent * 10 + "%");
        if (remain == total) {
            System.out.print("\n");
        }
    }
}
