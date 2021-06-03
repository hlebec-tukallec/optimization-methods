package utils;

import java.util.Arrays;

public class Point {
    public final double[] coordinates;

    public Point(double[] coordinates) {
        this.coordinates = coordinates;
    }

    public Point(Point p) {
        int n = p.coordinates.length;
        this.coordinates = new double[n];
        System.arraycopy(p.coordinates, 0, coordinates, 0, n);
    }

    public static Point multiplyOnScalar(final Point x, final Double l) {
        int n = x.coordinates.length;
        double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            c[i] = x.coordinates[i] * l;
        }
        return new Point(c);
    }

    public static Point minus(final Point x, final Point y) {
        int n = x.coordinates.length;
        double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            c[i] = x.coordinates[i] - y.coordinates[i];
        }
        return new Point(c);
    }

    public static Point multiplyMatrixAndPoint(final double[][] m, final Point x) {
        int n = m.length;
       double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i] += m[i][j] * x.coordinates[j];
            }
        }
        return new Point(c);
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

    public void plus(final Point y) {
        for (int i = 0; i < y.coordinates.length; i++) {
            coordinates[i] += y.coordinates[i];
        }
    }

    public static Point plus(final Point x, final Point y) {
        int n = x.coordinates.length;
        double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            c[i] = x.coordinates[i] + y.coordinates[i];
        }
        return new Point(c);
    }

    public static Point negative(Point x) {
        return new Point(Arrays.stream(x.coordinates)
                .map(a -> -a).toArray());
    }
}
