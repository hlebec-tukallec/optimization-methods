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

    public static MyPoint multiplyOnScalar(final MyPoint x, final Double l) {
        int n = x.coordinates.length;
        double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            c[i] = x.coordinates[i] * l;
        }
        return new MyPoint(c);
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

    public static MyPoint plus(final MyPoint x, final MyPoint y) {
        int n = x.coordinates.length;
        double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            c[i] = x.coordinates[i] + y.coordinates[i];
        }
        return new MyPoint(c);
    }

    public static MyPoint negative(MyPoint x) {
        return new MyPoint(Arrays.stream(x.coordinates)
                .map(a -> -a).toArray());
    }
}
