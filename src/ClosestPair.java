public class ClosestPair {
    private Point point1;
    private Point point2;
    private double distanceBetweenPoints;

    public ClosestPair(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
        this.distanceBetweenPoints = Utility.distance(point1, point2);
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public double getDistanceBetweenPoints() {
        return distanceBetweenPoints;
    }

    @Override
    public String toString() {
        return "ClosestPair{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                ", distanceBetweenPoints=" + distanceBetweenPoints +
                '}';
    }
}

