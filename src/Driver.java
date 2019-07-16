import java.util.*;

public class Driver
{
    public static void main(String[] args) {
        System.out.println("Works");

        List<Point> points = new ArrayList<>();
        points.add(new Point(4,4));
        points.add(new Point(7,7));
        points.add(new Point(1,1));
        points.add(new Point(10,1));
        points.add(new Point(3,3));

        int counter = 0;
        for (Point point : points) {
            point.setIndex(counter);
            counter++;
        }

        List<List<Point>> parameter = new ArrayList<>();
        parameter.add(points);
        parameter.add(points);

        ClosestPairLogic logic = new ClosestPairLogic();
//        List<List<Point>> sortedPoints = logic.presort(points);
//        ClosestPair closestPair = logic.closestPair(sortedPoints);
//        System.out.println("return value \n" + closestPair.toString());


        List<Point> randomPoints = Utility.generateRandomPoints(8, 20, 2, true);
        ClosestPair bruteforce = Utility.bruteforce(randomPoints);

//        List<List<Point>> testPresort = logic.presort(randomPoints);
//        ClosestPair testReturn = logic.closestPair(testPresort);
//        System.out.println("ClosestPairAlgo: " + testReturn);
//        System.out.println("BruteForce: " + bruteforce);

        List<List<Point>> lists = Utility.verifyAlgorithm();
        if (lists != null){
            System.out.println("points \n" + lists);
            System.out.println();
            ClosestPair closestPair = logic.closestPair(lists, "TOP");
            ;
        }


    }
}
