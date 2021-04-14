public class FastDown implements Method {
    private static double EPS;
    private final Source source;

    public FastDown(Source source) {
        this.source = source;
        EPS = source.EPS;
    }


    private Point fastDownImpl() {
        Point cur = source.point, gradient;
        do {
            gradient = source.gradient.apply(cur);
            double lambda = calculateLambda(gradient, cur);
            cur = countNewPoint(cur, gradient, lambda);
        } while (getMod(gradient) >= source.EPS);
        return cur;
    }

    public void findMinimum() {
        Point ans = fastDownImpl();
        System.out.println(ans.x + " " + ans.y);
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
        return source.func.apply(new Point(p.x - x * grad.x, p.y - x * grad.y));
    }
}
