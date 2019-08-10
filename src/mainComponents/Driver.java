package mainComponents;

import factories.ThreeDFastFactory;
import factories.ThreeDSlowFactory;
import factories.TwoDFactory;
import interfaces.ClosestPairLogic;
import three_d_fast.ThreeDFastDividingStrategy;
import three_d_fast.ThreeDFastPresortStrategy;

import java.lang.reflect.Array;
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
        if (lists != null) {
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
        if (lists != null) {
            System.out.println("points \n" + lists);
        }
    }


    private static void test3dFast() {
        System.out.println("Testing the 3D-Fast implementation");
        ClosestPairLogic logic = new ClosestPairLogicImpl(3, new ThreeDFastFactory());
        List<Point> randomPoints = Utility.generateRandomPoints(200, 1502, 3, true);
//        System.out.println("points\n" + randomPoints.toString());

        ClosestPair bruteforce = Utility.bruteforce(randomPoints);

        System.out.println("One random samples test run: ");
        List<List<Point>> presortedPoints = logic.presort(randomPoints);
        ClosestPair testReturn = logic.closestPair(presortedPoints, "TOP");
        System.out.println("ClosestPairAlgo: " + testReturn);
        System.out.println("BruteForce: " + bruteforce);

//        ThreeDFastPresortStrategy presortStrategy = new ThreeDFastPresortStrategy();
//        List<List<Point>> presort = presortStrategy.presort(randomPoints);
//
//        ThreeDFastDividingStrategy dividingStrategy = new ThreeDFastDividingStrategy();
//        List[] lists = dividingStrategy.dividePoints(presort, 1000);
    }

    public static void main(String[] args) {
//        testCloning();

//        test2d();  // 2d in O(n lg n)
//        test3dSlow(); //3d-slow in O(n lg^2 n)


        test3dFast();

//        bigNumberRun();

//        testIsolated1();
//        testIsolated2();
//        testIsolated3();
//        testIsolated4();
    }

    private static void testCloning() {
        List<Point> org = new ArrayList<>();
        org.add(new Point(3, new double[]{0, 0, 0}));

        List<Point> copy = new ArrayList<>();
//        copy.addAll(org);

        for (Point p : org) {
            try {
                copy.add(p.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("before changing\n org: \n" + org + "\ncopy\n" + copy);
        copy.get(0).setCoordinates(new double[]{5, 5, 5});
        System.out.println("after changing\n org: \n" + org + "\ncopy\n" + copy);
        System.out.println();
        System.out.println();
        System.out.println();
    }


    private static void testIsolated1() {
        System.out.println("Isolated test 1: ");
        Point p1 = new Point(3, new double[]{24, 52, 142});
        Point p3 = new Point(3, new double[]{54, 46, 38});
        Point p5 = new Point(3, new double[]{104, 24, 28});
        Point p2 = new Point(3, new double[]{123, 141, 130});
        Point p4 = new Point(3, new double[]{123, 34, 14});
        Point p6 = new Point(3, new double[]{146, 122, 147});

        ArrayList<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);

        ClosestPairLogic logic = new ClosestPairLogicImpl(3, new ThreeDFastFactory());
        List<List<Point>> presort = logic.presort(points);
        ClosestPair topTest = logic.closestPair(presort, "topTest");

        ClosestPair brute = Utility.bruteforce(points);
        System.out.println("brute " + brute);
        System.out.println("CP " + topTest);
    }

    private static void testIsolated2() {
        System.out.println("Isolated test 2: ");
        Point p1 = new Point(3, new double[]{12, 92, 42});
        Point p3 = new Point(3, new double[]{26, 148, 81});
        Point p5 = new Point(3, new double[]{30, 69, 129});
        Point p2 = new Point(3, new double[]{36, 2, 102});
        Point p4 = new Point(3, new double[]{44, 70, 32});
        Point p6 = new Point(3, new double[]{77, 80, 57});

        ArrayList<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);

        ClosestPairLogic logic = new ClosestPairLogicImpl(3, new ThreeDFastFactory());
        List<List<Point>> presort = logic.presort(points);
        ClosestPair topTest = logic.closestPair(presort, "topTest");

        ClosestPair brute = Utility.bruteforce(points);
        System.out.println("brute " + brute);
        System.out.println("CP " + topTest);
    }

    private static void testIsolated3() {
        System.out.println("Isolated test 3: ");
        Point p1 = new Point(3, new double[]{18, 6, 41});
        Point p3 = new Point(3, new double[]{91, 62, 116});
        Point p5 = new Point(3, new double[]{104, 121, 37});
        Point p2 = new Point(3, new double[]{121, 137, 143});
        Point p4 = new Point(3, new double[]{121, 138, 115});
        Point p6 = new Point(3, new double[]{128, 141, 140});

        ArrayList<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);

        ClosestPair brute = Utility.bruteforce(points);
        System.out.println("brute " + brute);

        ClosestPairLogic logic = new ClosestPairLogicImpl(3, new ThreeDFastFactory());
        List<List<Point>> presort = logic.presort(points);
        ClosestPair topTest = logic.closestPair(presort, "topTest");
        System.out.println("CP " + topTest);
    }

    private static void testIsolated4() {
        System.out.println("Isolated test 4: ");

        Point p1 = new Point(3, new double[]{ 64.0, 57.0, 132.0});
        Point p2 = new Point(3, new double[]{ 51.0, 43.0, 44.0});
        Point p3 = new Point(3, new double[]{ 79.0, 31.0, 16.0});
        Point p5 = new Point(3, new double[]{ 116.0, 67.0, 140.0});
        Point p4 =  new Point(3, new double[]{ 135.0, 130.0, 84.0});
        Point p6 =  new Point(3, new double[]{ 85.0, 74.0, 32.0});

        ArrayList<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);

        ClosestPair brute = Utility.bruteforce(points);
        System.out.println("brute " + brute);

        ClosestPairLogic logic = new ClosestPairLogicImpl(3, new ThreeDFastFactory());
        List<List<Point>> presort = logic.presort(points);
        ClosestPair topTest = logic.closestPair(presort, "topTest");
        System.out.println("CP " + topTest);
    }


    private static void bigNumberRun() {
        ClosestPairLogic logic = new ClosestPairLogicImpl(3, new ThreeDSlowFactory());
        System.out.println("Generating random points");
        List<Point> randomPoints = Utility.generateRandomPoints(7000000, 1000000, 3, true); // by false to double

        System.out.println("Testing for 7.000.000 points.");
        ClosestPair result = logic.closestPair(logic.presort(randomPoints), "Top");
        System.out.println("The final result\n" + result);
    }
}
