package mainComponents;

public class Point {
    private int index;
    private double[] coordinates;
    private int dimension;

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
}
