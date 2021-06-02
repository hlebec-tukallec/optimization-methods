import java.util.Arrays;

public class Newton {
    public Point minimum(final MyFunction f, final Point x0, final double eps) {
        Point p, x = new Point(x0);
        do {
            p = slay(f.getHessianValue(x), f.getGradientValue(x));
            x.plus(p);
        } while (norm(p) < eps);
        return x;
    }

    private double norm(final Point p) {
        return Math.sqrt(Arrays.stream(p.coordinates).map(x -> x*x).sum());
    }

    private Point slay(Double[][] h, Double[] f) {
        int n = f.length;
        for (int i = 0; i < n; i++) {
            f[i] = -f[i];
        }

        int[] realRows = new int[n];
        for (int i = 0; i < n; i++) {
            realRows[i] = i;
        }

        for (int row = 0; row < n; row++) {
            int sel = row;
            for (int i = row + 1; i < n; i++) {
                if (Math.abs(h[realRows[i]][row]) > Math.abs(h[realRows[sel]][row])) {
                    sel = i;
                }
            }
            int tmp = realRows[sel];
            realRows[sel] = realRows[row];
            realRows[row] = tmp;

            for (int i = row + 1; i < n; ++i) {
                double c = h[realRows[i]][row] / h[realRows[row]][row];
                for (int j = row; j < n; ++j) {
                    h[realRows[i]][j] -= h[realRows[row]][j] * c;
                }
                f[realRows[i]] -= f[realRows[row]] * c;
            }
        }

        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = n - 1; j > i; j--) {
                sum += h[realRows[i]][j] * solution[j];
            }
            solution[i] = (f[realRows[i]] - sum) / h[realRows[i]][i];
        }
        return new Point(solution);
    }
}
