import matrixes.SparseRowColMatrix;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class ConjugateGradientSLAE {
    final double EPS = 0.0000001;

    public double[] solveSLAE(SparseRowColMatrix matrix) {
        int n = matrix.getN();
        double[] curX = getX0(n), curGradient = getGradient(curX, matrix);
        double[] curP = multiply(curGradient, -1);
        do {
            for (int i = 0; i < n && getMod(curGradient) > EPS; i++) {
                double[] curApK = multiply(matrix, curP);
                double alfa = Math.pow(getMod(getGradient(curX, matrix)), 2) /
                        multiply(curApK, curP);
                double[] newX = countNew(curX, curP, alfa);
                double[] newGradient = countNew(curGradient, curApK, alfa);
                double betta = (i == 0) ? 0 : Math.pow(getMod(getGradient(newX, matrix)), 2) /
                        Math.pow(getMod(getGradient(curX, matrix)), 2);
                double[] newP = add(
                        multiply(newGradient, -1),
                        multiply(curP, betta));

                curX = newX;
                curP = newP;
                curGradient = newGradient;
            }
        } while (getMod(curGradient) > EPS);
        return curX;
    }

    private double[] countNew(double[] p, double[] grad, double lambda) {
        int n = p.length;
        double[] coordinates = new double[n];
        for (int i = 0; i < n; i++) {
            coordinates[i] = p[i] + lambda * grad[i];
        }
        return coordinates;
    }

    private double getMod(final double[] x) {
        return Math.sqrt(Arrays.stream(x).map(y -> y * y).sum());
    }

    private double[] getGradient(final double[] x, final SparseRowColMatrix matrix) {
        return minus(multiply(matrix, x), matrix.b);
    }

    private double[] getX0(final int n) {
        return DoubleStream.generate(() -> 1).limit(n).toArray();
    }

    private double[] add(final double[] x, final double[] y) {
        int n = x.length;
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            res[i] = x[i] + y[i];
        }
        return res;
    }

    private double multiply(final double[] x, final double[] y) {
        double res = 0;
        for (int i = 0; i < x.length; i++) {
            res += x[i] * y[i];
        }
        return res;
    }

    private double[] multiply(final double[] x, final double i) {
        return Arrays.stream(x).map(y -> i * y).toArray();
    }

    public static double[] multiply(final SparseRowColMatrix m, final double[] x) {
        int n = m.getN();
        double[] c = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                c[i] += m.getIJ(i, j) * x[j];
            }
        }
        return c;
    }

    private double[] minus(final double[] x, final double[] y) {
        int n = x.length;
        double[] res = new double[n];
        for (int i = 0; i < n; i++) {
            res[i] = x[i] - y[i];
        }
        return res;
    }
}
