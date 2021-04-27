import java.util.Arrays;

public interface Method {
    double EPS = 0.0001;

    Point count();

    default Point countNewPoint(Point p, Point grad, double lambda) {
        int n = p.getCoordinates().length;
        double[] coordinates = new double[n];
        for (int i = 0; i < n; i++) {
            coordinates[i] = p.getCoordinates()[i] - lambda * grad.getCoordinates()[i];
        }
        return new Point(coordinates);
    }

    default double getMod(Point p) {
        return Math.sqrt(Arrays.stream(p.getCoordinates()).map(x -> x * x).sum());
    }
}