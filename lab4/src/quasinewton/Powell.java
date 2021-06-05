package quasinewton;

import utils.ExtendedFunction;
import utils.Method;
import utils.Point;

public class Powell implements Method {
    private int iter = 0;
    @Override
    public Point minimum(final ExtendedFunction f, final Point x0, final double eps) {
        Point x = new Point(x0);
        Point w = Point.negative(new Point(f.getGradientValue(x)));
        int n = w.coordinates.length;
        double[][] H = createE(n);

        while (norm(w) >= eps) {
            iter++;
            Point p = Point.multiplyMatrixAndPoint(H, w);
            double alpha = countLambda(f, x, p);
            Point d = Point.multiplyOnScalar(p, alpha);
            Point nextX = Point.plus(x, d);

            Point nextW = Point.negative(new Point(f.getGradientValue(nextX)));
            H = getNextH(H, Point.minus(nextX, x), Point.minus(nextW, w));
            x = nextX;
            w = nextW;
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
        return Point.minusMatrixes(H, Point.multiplyMatrixOnScalar(Point.multiplyVectorOnVector(s, s),p));
    }
}
