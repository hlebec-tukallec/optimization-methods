public abstract class CommonDown implements Method {
    private final Source source;

    public CommonDown(Source source) {
        this.source = source;
    }

    double simplify(double x, Point grad, Point p) {
        return source.func.apply(new Point(p.x - x * grad.x, p.y - x * grad.y));
    }

    private Point cnt(Point p, Point grad, double lambda) {
        return new Point(p.x - lambda * grad.x, p.y - lambda * grad.y);
    }

    private Point gradDownImpl() {
        Point tmp = source.point;
        Point last;

        do {
            last = tmp;
            Point grady = source.gradient.apply(tmp);
            double lambda = 2;
            lambda = calculateLambda(grady, tmp, lambda);
            tmp = cnt(tmp, grady, lambda);

        } while (Math.abs(source.func.apply(tmp) - source.func.apply(last)) > source.EPS);
        return tmp;
    }

    abstract double calculateLambda(Point grady, Point tmp, double lambda);

    public void count() {
        Point ans = gradDownImpl();
        System.out.println(ans.x + " " + ans.y);
    }
}
