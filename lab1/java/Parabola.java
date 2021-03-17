public class Parabola implements Method {

    @Override
    public double count() {
        double x1 = leftBound, x2, x3 = rightBound;
        double f1 = Method.f(x1), f3 = Method.f(x3);
        x2 = findPoint(x1, x3, f1, f3);
        double f2 = Method.f(x2), fx;
        double prevX = 0;
        double x = 1;

        while (Math.abs(x - prevX) > EPS) {
            prevX = x;
            x = newX(x1, x2, x3, f1, f2, f3);
            fx = Method.f(x);
            if (x2 < x) {
                if (f2 < fx) {
                    x3 = x;
                    f3 = fx;
                } else {
                    x1 = x2;
                    f1 = f2;
                    x2 = x;
                    f2 = fx;
                }
            } else {
                if (fx < f2) {
                    x3 = x2;
                    f3 = f2;
                    x2 = x;
                    f2 = fx;
                } else {
                    x1 = x;
                    f1 = fx;
                }
            }
        }
        return (x1 + x3) / 2;
    }

    private double newX(double x1, double x2, double x3, double f1, double f2, double f3) {
        return 0.5 * (x1 + x2 - ((f2 - f1) * (x3 - x2) / (x2 - x1)
                / ((f3 - f1) / (x3 - x1) - (f2 - f1) / (x2 - x1))));
    }

    private double findPoint(double x, double y, double fx, double fy) {
        double z, fz;
        while (true) {
            z = (x + y) / 2;
            fz = Method.f(z);
            if (fz <= fx && fz <= fy) {
                return z;
            } else if (fz > fy) {
                x = z;
                fx = fz;
            }
            if (fz > fx) {
                y = z;
                fy = fz;
            }
        }
    }
}
