public class SteepestDescentMethod implements Method {
    private final Source source;
    int iter = 0;

    public SteepestDescentMethod(Source source) {
        this.source = source;
    }

    @Override
    public Point count() {
        Point cur = source.point, gradient;
        do {
            gradient = source.getGradient(cur);
            double lambda = calculateLambda(gradient, cur);
            cur = countNewPoint(cur, gradient, lambda);
            iter++;
        } while (getMod(gradient) >= EPS);
        return cur;
    }

    public double calculateLambda(Point grad, Point p) {
        final double phi = 1.6180339887;
        double x1, x2, y1, y2;
        double l = 0;
        double r = 0.01;

        x1 = r - ((r - l) / phi);
        x2 = l + ((r - l) / phi);
        y1 = simplify(x1, grad, p);
        y2 = simplify(x2, grad, p);

        while (Math.abs(r - l) > EPS) {
            if (y1 <= y2) {
                r = x2;
                x2 = x1;
                x1 = r - ((r - l) / phi);
                y2 = y1;
                y1 = simplify(x1, grad, p);
            } else {
                l = x1;
                x1 = x2;
                x2 = l + ((r - l) / phi);
                y1 = y2;
                y2 = simplify(x2, grad, p);
            }
        }
        return (r + l) / 2;
    }

    double simplify(double x, Point grad, Point p) {
        return source.getFunctionValue(countNewPoint(p, grad, x));
    }
}
