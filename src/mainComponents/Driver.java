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

        List<List<Point>> lists = Utility.verifyAlgorithm(3, 100, 5000, 1000, new ThreeDSlowFactory());
        if (lists != null) {
            System.out.println("points \n" + lists);
        }
    }


    private static void test3dFast() {
        System.out.println("Testing the 3D-Fast implementation");
        ClosestPairLogic logic = new ClosestPairLogicImpl(3, new ThreeDFastFactory());
        List<Point> randomPoints = Utility.generateRandomPoints(22, 100, 3, true);
//        System.out.println("points\n" + randomPoints.toString());

        ClosestPair bruteforce = Utility.bruteforce(randomPoints);

        System.out.println("One random samples test run: ");
        List<List<Point>> presortedPoints = logic.presort(randomPoints);
        ClosestPair testReturn = logic.closestPair(presortedPoints, "TOP");
        System.out.println("ClosestPairAlgo: " + testReturn);
        System.out.println("BruteForce: " + bruteforce);

        List<List<Point>> lists = Utility.verifyAlgorithm(3, 200, 5000, 1000, new ThreeDFastFactory());
//        List<List<Point>> lists = Utility.verifyAlgorithm(3, 200, 6, 100, new ThreeDFastFactory());
        if (lists != null) {
            System.out.println("points \n" + lists.get(0));
        }
    }

    public static void main(String[] args) {
        test2d();  // 2d in O(n lg n)
        test3dSlow(); //3d-slow in O(n lg^2 n)
        test3dFast();

//        bigNumberRun();

//        testIsolated1();
//        testIsolated2();
//        testIsolated3();
//        testIsolated4();
//        testIsolated5();
//        testIsolated6();
//        testIsolated7();
//        testIsolated8();
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

    private static void testIsolated5() {
        System.out.println("Isolated test 5: ");
        Point p1 = new Point(3, new double[]{ 20,60,81});
        Point p2 = new Point(3, new double[]{ 23,43,45});
        Point p3 = new Point(3, new double[]{ 24,61,60});
        Point p4 =  new Point(3, new double[]{ 88,59,78});


        ArrayList<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);

        ClosestPair brute = Utility.bruteforce(points);
        System.out.println("brute " + brute);

        ClosestPairLogic logic = new ClosestPairLogicImpl(3, new ThreeDFastFactory());
        List<List<Point>> presort = logic.presort(points);
        ClosestPair topTest = logic.closestPair(presort, "topTest");
        System.out.println("CP " + topTest);
    }

    private static void testIsolated6() {
        System.out.println("Isolated test 6: ");
        Point p1 = new Point(3, new double[]{ 18,8,74});
        Point p2 = new Point(3, new double[]{ 26,49,10});
        Point p3 = new Point(3, new double[]{ 28,73,99});
        Point p4 =  new Point(3, new double[]{ 51,40,9});
        Point p5 =  new Point(3, new double[]{ 56,63,37});
        Point p6 =  new Point(3, new double[]{ 62,31,28});
        Point p7 =  new Point(3, new double[]{ 99,30,76});


        ArrayList<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);

        ClosestPair brute = Utility.bruteforce(points);
        System.out.println("brute " + brute);

        ClosestPairLogic logic = new ClosestPairLogicImpl(3, new ThreeDFastFactory());
        List<List<Point>> presort = logic.presort(points);
        ClosestPair topTest = logic.closestPair(presort, "topTest");
        System.out.println("CP " + topTest);
    }

    private static void testIsolated7() {
        System.out.println("Isolated test 7: ");
        Point p1 = new Point(3, new double[]{ 21,11,81});
        Point p2 = new Point(3, new double[]{ 49,3,47});
        Point p3 = new Point(3, new double[]{ 49,56,84});
        Point p4 =  new Point(3, new double[]{ 55,26,34});
        Point p5 =  new Point(3, new double[]{ 74,74,12});
        Point p6 =  new Point(3, new double[]{ 90,45,10});
        Point p7 =  new Point(3, new double[]{ 90,15,6});


        ArrayList<Point> points = new ArrayList<>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        points.add(p5);
        points.add(p6);
        points.add(p7);

        ClosestPair brute = Utility.bruteforce(points);
        System.out.println("brute " + brute);

        ClosestPairLogic logic = new ClosestPairLogicImpl(3, new ThreeDFastFactory());
        List<List<Point>> presort = logic.presort(points);
        ClosestPair topTest = logic.closestPair(presort, "topTest");
        System.out.println("CP " + topTest);
    }

    private static void testIsolated8() {
        System.out.println("Isolated test 8: ");
        Point p1 = new Point(3, new double[]{ 16,61,47});
       Point p2 = new Point(3, new double[]{ 35,26,2});
        Point p3 = new Point(3, new double[]{ 51,1,79});
        Point p4 =  new Point(3, new double[]{ 55,56,62});
        Point p5 =  new Point(3, new double[]{ 79,63,30});
        Point p6 =  new Point(3, new double[]{ 82,38,65});


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
