package mainComponents;

import Factories.ThreeDSlowFactory;
import Factories.TwoDFactory;
import interfaces.ClosestPairLogic;

import javax.rmi.CORBA.Util;
import java.util.*;

public class Driver {
    public static void test2d() {
        System.out.println("Testing the 2D implementation");

        List<Point> points = new ArrayList<>();
        points.add(new Point(2, new double[]{4, 4}));
        points.add(new Point(2, new double[]{7, 7}));
        points.add(new Point(2, new double[]{1, 1}));
        points.add(new Point(2, new double[]{10, 1}));
        points.add(new Point(2, new double[]{3, 3}));

        List<List<Point>> parameter = new ArrayList<>();
        parameter.add(points);
        parameter.add(points);

        ClosestPairLogic logic = new ClosestPairLogicImpl(2, new TwoDFactory());
        List<List<Point>> sortedPoints = logic.presort(points);
        ClosestPair closestPair = logic.closestPair(sortedPoints, "TOP");
//        System.out.println("return value \n" + closestPair.toString());


        List<Point> randomPoints = Utility.generateRandomPoints(8, 20, 2, true);
        ClosestPair bruteforce = Utility.bruteforce(randomPoints);

        System.out.println("One random samples test run: ");
        List<List<Point>> testPresort = logic.presort(randomPoints);
        ClosestPair testReturn = logic.closestPair(testPresort, "TOP");
        System.out.println("ClosestPairAlgo: " + testReturn);
        System.out.println("BruteForce: " + bruteforce);

        List<List<Point>> lists = Utility.verifyAlgorithm(2, 200, 5000, 1000, new TwoDFactory());
        if (lists != null){
            System.out.println("points \n" + lists);
            System.out.println();
//            ClosestPair closestPair = logic.closestPair(lists, "TOP");
        }
    }

    private static void test3dSlow() {
        System.out.println("Testing the 3D-Slow implementation");
        ClosestPairLogic logic = new ClosestPairLogicImpl(3, new ThreeDSlowFactory());
        List<Point> randomPoints = Utility.generateRandomPoints(10, 150, 3, true);

        ClosestPair bruteforce = Utility.bruteforce(randomPoints);

        System.out.println("One random samples test run: ");
        List<List<Point>> presortedPoints = logic.presort(randomPoints);
        ClosestPair testReturn = logic.closestPair(presortedPoints, "TOP");
        System.out.println("ClosestPairAlgo: " + testReturn);
        System.out.println("BruteForce: " + bruteforce);

        List<List<Point>> lists = Utility.verifyAlgorithm(3, 200, 5000, 1000, new ThreeDSlowFactory());
        if (lists != null){
            System.out.println("points \n" + lists);
            System.out.println();
//            ClosestPair closestPair = logic.closestPair(lists, "TOP");
        }
    }


    public static void main(String[] args) {
        test2d();
        test3dSlow();
    }
}
