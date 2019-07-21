package mainComponents;

import Factories.TwoDFactory;
import interfaces.ClosestPairLogic;
import two_d.TwoDDividingStrategy;
import two_d.TwoDPresortStrategy;
import two_d.TwoDSlabStrategy;

import java.util.*;

public class Driver {
    public static void main(String[] args) {
        System.out.println("Works");

        List<Point> points = new ArrayList<>();
        points.add(new Point(2, new double[]{4, 4}));
        points.add(new Point(2, new double[]{7, 7}));
        points.add(new Point(2, new double[]{1, 1}));
        points.add(new Point(2, new double[]{10, 1}));
        points.add(new Point(2, new double[]{3, 3}));


        int counter = 0;
        for (Point point : points) {
            point.setIndex(counter);
            counter++;
        }

        List<List<Point>> parameter = new ArrayList<>();
        parameter.add(points);
        parameter.add(points);

        ClosestPairLogic logic = new ClosestPairLogicImpl(2, new TwoDFactory());
//        List<List<Point>> sortedPoints = logic.presort(points);
//        ClosestPair closestPair = logic.closestPair(sortedPoints);
//        System.out.println("return value \n" + closestPair.toString());


        System.out.println("generate random points");
        List<Point> randomPoints = Utility.generateRandomPoints(8, 20, 2, true);
        System.out.println("Trying bruteforce");
        ClosestPair bruteforce = Utility.bruteforce(randomPoints);

        List<List<Point>> testPresort = logic.presort(randomPoints);
        ClosestPair testReturn = logic.closestPair(testPresort, "TOP");
        System.out.println("ClosestPairAlgo: " + testReturn);
        System.out.println("BruteForce: " + bruteforce);

        List<List<Point>> lists = Utility.verifyAlgorithm(2, 200, 5000, 1000, new TwoDFactory());
//        if (lists != null){
//            System.out.println("points \n" + lists);
//            System.out.println();
//            ClosestPair closestPair = logic.closestPair(lists, "TOP");
//        }


    }
}