public class GradDown implements Method {

    private point gradient(point p) {
        return new point(200 * p.x, 200 * p.y);
    }

    private double simplify(double x, point grad, point p) {
        return Method.f(new point(p.x - x * grad.x, p.y - x * grad.y));
    }

    private double Golden(point grad, point p) {
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

    private point cnt(point p, point grad, double lambda) {
        return new point(p.x - lambda * grad.x, p.y - lambda * grad.y);
    }

    private point gradDown() {
        point tmp = a;
        point last;

        do {
            last = tmp;
            point grady = gradient(tmp);
            double lambda = Golden(grady, tmp);
            tmp = cnt(tmp, grady, lambda);

        } while (Math.abs(Method.f(tmp) - Method.f(last)) > EPS);
        return tmp;
    }

    @Override
    public void count() {
        point ans = gradDown();
        System.out.println(ans.x + " " + ans.y);
    }

}
