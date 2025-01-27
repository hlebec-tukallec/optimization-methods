package utils;

import java.util.Arrays;
import java.util.function.Function;

public interface Method {
    Point minimum(ExtendedFunction f, Point x0, double eps);

    int getCountOfIterations();

    default Point slay(double[][] h, double[] f) {
        int n = f.length;

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

            tmp = realRows[sel];
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

    default double norm(final Point p) {
        return Math.sqrt(Arrays.stream(p.coordinates).map(x -> x * x).sum());
    }

    default double countLambda(final ExtendedFunction function, Point x, Point d) {
        Function<Double, Double> f = l -> function.getFValue(Point.plus(x, Point.multiplyOnScalar(d, l)));
        final double PHI = 2 - (1 + Math.sqrt(5)) / 2;
        final double EPS = 0.00001;

        double a = -1000, b = 1000, x1, x2, f1, f2; //todo как выбирать a и b?
        x1 = a + PHI * (b - a);
        x2 = b - PHI * (b - a);
        f1 = f.apply(x1);
        f2 = f.apply(x2);
        while (Math.abs(b - a) > EPS) {
            if (f1 < f2) {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = a + PHI * (b - a);
                f1 = f.apply(x1);
            } else {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = b - PHI * (b - a);
                f2 = f.apply(x2);
            }
        }
        return (x1 + x2) / 2;
    }

    default double multiplyPoints(Point x, Point y) {
        double sum = 0;
        for (int i = 0; i < x.coordinates.length; i++) {
            sum += x.get(i)*y.get(i);
        }
        return sum;
    }

    default double[][] createE(int n) {
        double[][] H = new double[n][n];
        for (int i = 0; i < n; i++) {
            H[i][i] = 1;
        }
        return H;
    }
}
