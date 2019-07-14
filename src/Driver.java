import java.util.*;

public class Driver
{
    public static void main(String[] args) {
        System.out.println("Works");

        List<Point> points = new ArrayList<>();
        points.add(new Point(1,1));
        points.add(new Point(3,3));
        points.add(new Point(4,4));
        points.add(new Point(7,7));
        points.add(new Point(10,10));

        int counter = 0;
        for (Point point : points) {
            point.setIndex(counter);
            counter++;
        }

        List<List<Point>> parameter = new ArrayList<>();
        parameter.add(points);
        parameter.add(points);

        ClosestPairLogic logic = new ClosestPairLogic();
        ClosestPair closestPair = logic.closestPair(parameter);

        System.out.println("return value \n" + closestPair.toString());

    }
}
