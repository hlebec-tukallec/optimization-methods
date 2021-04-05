import java.util.function.Function;

public abstract class CommonDown implements Method {
    private Function<Points, Double> minimizationFun;

    public CommonDown() {
    }

    public CommonDown(Function<Points, Double> minimizationFun) {
        this.minimizationFun = minimizationFun;
    }

    protected static double simplify(double x, Point grad, Point p) {
        return Method.f1(new Point(p.x - x * grad.x, p.y - x * grad.y));
    }

    private Point cnt(Point p, Point grad, double lambda) {
        return new Point(p.x - lambda * grad.x, p.y - lambda * grad.y);
    }

    private Point gradDown() {
        Point tmp = a;
        Point last;

        do {
            last = tmp;
            Point grady = Method.f1Grad(tmp);
            //Point grady = Method.f2Grad(tmp);

            double lambda = minimizationFun.apply(new Points(grady, tmp));
            tmp = cnt(tmp, grady, lambda);

        } while (Math.abs(Method.f1(tmp) - Method.f1(last)) > EPS);
        return tmp;
    }

    @Override
    public void count() {
        Point ans = gradDown();
        System.out.println(ans.x + " " + ans.y);
    }
}
