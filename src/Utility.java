public class Utility {

    //TODO: Support any dimension. Now it just supports two dim.
    //Calculate the euclidian distance
    public static double distance(Point point1, Point point2) {

        double temp = Math.pow((point1.getX() - point2.getX()), 2) + Math.pow((point1.getY() - point2.getY()),2);

        return Math.sqrt(temp);
    }


}
