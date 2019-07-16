import java.util.*;

public class Utility {

    //TODO: Support any dimension. Now it just supports two dim.
    //Calculate the euclidian distance
    public static double distance(Point point1, Point point2) {

        double temp = Math.pow((point1.getX() - point2.getX()), 2) + Math.pow((point1.getY() - point2.getY()), 2);

        return Math.sqrt(temp);
    }

    //TODO: Make more dynamic and generic
    public static List<Point> generateRandomPoints(int numberOfPoints, int upperBound, int dimension, boolean asInt) {
        Set<Point> points = new HashSet<>();

        while (points.size() < numberOfPoints) {
            double[] coordinate = new double[dimension];
            for (int j = 0; j < dimension; j++) {
                if (asInt) { //meaning wihh .000
                    int number = (int) (Math.random() * upperBound);
                    coordinate[j] = ((double) number);
                    int k = 2;
                } else {
                    coordinate[j] = Math.random() * upperBound;
                }
            }
            Point p = new Point(0, 0);
            p.setX(coordinate[0]);
            p.setY(coordinate[1]);

            points.add(p);
        }

        List<Point> returnList = new ArrayList<>();
        returnList.addAll(points);

        return returnList;
    }


    public static ClosestPair bruteforce(List<Point> points) {
        ClosestPair cp = new ClosestPair(points.get(0), points.get(1));
        for (Point point1 : points) {
            for (Point point2 : points) {
            if (point1.getX() == point2.getX() && point1.getY() == point2.getY()) {
                    continue;
                }
                double distance = distance(point1, point2);
                if (distance < cp.getDistanceBetweenPoints()) {
                    cp = new ClosestPair(point1, point2);
                }
            }
        }
        return cp;
    }

    public static List<List<Point>> verifyAlgorithm() {
        // for know hardcoded to support 2d
        System.out.println("Verify algorithm");
        ClosestPairLogic logic = new ClosestPairLogic();

        int iterations = 5000;
        for (int i = 0; i < iterations; i++) {
            progressPercentage(i+1, iterations);
            int numberOfPoints = (int) (Math.random() * 8000) + 2;
//            int numberOfPoints = 15;
            int upperBound = 1000;

            List<Point> points = generateRandomPoints(numberOfPoints, upperBound, 2, true);

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
