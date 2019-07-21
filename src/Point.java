public class Point {
    private double x;
    private double y;
    private int index;
    private double[] coordinates;
    private int dimension;

//    public Point(double x, double y) {
//        this.x = x;
//        this.y = y;
//    }

    public Point(int dimension, double[] coordinates){
        this.dimension = dimension;

        if (dimension != coordinates.length){
            throw new RuntimeException("There was a mismatch between the dimension and the coordiantes given. " +
                    "The dimension given was: " + dimension +  ". The dimension of the coordinates was: " + coordinates.length);
        }

        this.coordinates = coordinates;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public int getDimension() {
        return dimension;
    }

    public void setDimension(int dimension) {
        this.dimension = dimension;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + coordinates[0] +
                ", y=" + coordinates[1] +
                ", index=" + index +
                '}';
    }


    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(x);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(y);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    //    @Override
//    public int compareTo(Object o) {
//        return x.compareTo(((Point)o).getX());
//    }
}
