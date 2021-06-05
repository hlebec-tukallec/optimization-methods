package quasinewton;

import utils.ExtendedFunction;
import utils.Method;
import utils.Point;

public class BroadenFletcherChen implements Method {
    private int iter = 0;
    @Override
    public Point minimum(final ExtendedFunction f, final Point x0, final double eps) {
        Point x = new Point(x0);
        Point gradient = new Point(f.getGradientValue(x));
        int n = gradient.coordinates.length;
        double[][] H = createE(n);
        System.out.println(x);
        while (norm(gradient) >= eps) {
            iter++;
            Point p = Point.negative(Point.multiplyMatrixAndPoint(H, gradient));
            double alpha = countLambda(f, x, p);
            Point d = Point.multiplyOnScalar(p, alpha);
            Point nextX = Point.plus(x, d);

            Point nextGrad = new Point(f.getGradientValue(nextX));
            H = getNextH(H, Point.minus(nextX, x), Point.minus(nextGrad, gradient));
            x = nextX;
            gradient = nextGrad;
            System.out.println(x);
        }
        return x;
    }

    @Override
    public int getCountOfIterations() {
        return iter;
    }

    private double[][] getNextH(final double[][] H, final Point sPoint, final Point yPoint) {
        double[] s = sPoint.coordinates;
        double[] y = yPoint.coordinates;
        int n = s.length;

        double del = 0;
        for (int i = 0; i < n; i++) {
            del += y[i] * s[i];
        }
        double p = 1 / del;

        double[][] nextC = Point.minusMatrixes(createE(n), Point.multiplyMatrixOnScalar(Point.multiplyVectorOnVector(s, y), p));
        nextC = Point.multiplyMatrixOnMatrix(nextC, H);
        nextC = Point.multiplyMatrixOnMatrix(nextC, Point.minusMatrixes(createE(n), Point.multiplyMatrixOnScalar(Point.multiplyVectorOnVector(y, s), p)));
        nextC = Point.plusMatrixes(nextC, Point.multiplyMatrixOnScalar(Point.multiplyVectorOnVector(s, s), p));
        return nextC;
    }
}
