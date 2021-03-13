public class Parabola implements Method {

    @Override
    public double count() {
        double x1 = leftBound, x2, x3 = rightBound;
        x2 = binSearch(x1, x3);
        double f1 = Method.f(x1), f2 = Method.f(x2), f3 = Method.f(x3), fx;
        double[] newVal;
        double prevX = 0;
        double x = 1;

        while (Math.abs(x - prevX) > EPS) {
            prevX = x;
            x = newX(x1, x2, x3, f1, f2, f3);
            fx = Method.f(x);
            if (x2 < x) {
                newVal = recount(x1, x2, x, x3, f1, f2, fx, f3);
            } else {
                newVal = recount(x1, x, x2, x3, f1, fx, f2, f3);
            }
            x1 = newVal[0];
            x2 = newVal[1];
            x3 = newVal[2];
            f1 = newVal[3];
            f2 = newVal[4];
            f3 = newVal[5];
        }
        return x;
    }

    private double binSearch(double x, double y) {
        double z, fz, fx = Method.f(x), fy = Method.f(x);
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

    private double newX(double x1, double x2, double x3, double f1, double f2, double f3) {
        return 0.5 * (x1 + x2 - ((f2 - f1) * (x3 - x2) / (x2 - x1)
                / ((f3 - f1) / (x3 - x1) - (f2 - f1) / (x2 - x1))));
    }

    private double[] recount(double x1, double a1, double a2, double x3, double f1, double fa1, double fa2, double f3) {
        double[] newVal = new double[6];
        if (Method.f(a1) > Method.f(a2)) {
            newVal[0] = a1;
            newVal[1] = a2;
            newVal[2] = x3;
            newVal[3] = fa1;
            newVal[4] = fa2;
            newVal[5] = f3;
        } else {
            newVal[0] = x1;
            newVal[1] = a1;
            newVal[2] = a2;
            newVal[3] = f1;
            newVal[4] = fa1;
            newVal[5] = fa2;
        }
        return newVal;
    }
}
