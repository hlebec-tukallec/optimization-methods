import java.util.Arrays;

public class MyPoint {
    public final double[] coordinates;

    public MyPoint(double[] coordinates) {
        this.coordinates = coordinates;
    }
    public MyPoint(MyPoint p) {
        int n = p.coordinates.length;
        this.coordinates = new double[n];
        System.arraycopy(p.coordinates, 0, coordinates, 0, n);
    }


    public double[] getCoordinates() {
        return coordinates;
    }

    public double get(int i) {
        return coordinates[i];
    }

    @Override
    public String toString() {
        return "Point{" +
                "coordinates=" + Arrays.toString(coordinates) +
                '}';
    }

    public void plus(final MyPoint y) {
        for (int i = 0; i < y.coordinates.length; i++) {
            coordinates[i] += y.coordinates[i];
        }
    }
}
