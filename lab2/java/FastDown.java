public class FastDown extends CommonDown {
    private static double EPS;

    public FastDown(Source source) {
        super(source);
        EPS = source.EPS;
    }

    @Override
    public double calculateLambda(Point grad, Point p, double lambda) {
        final double phi = 1.6180339887;
        double x1, x2, y1, y2;
        double l = 0;
        double r = 0.05;

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
}
