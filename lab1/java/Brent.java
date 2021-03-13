public class Brent implements Method {
    private final double k = (3 - Math.sqrt(5)) / 2;

    private boolean areDifferent(double a, double b, double c) {
        return a != b && a != c && b != c;
    }

    private double parabola(double x1, double x2, double x3, double f1, double f2, double f3) {
        return x2 - 0.5 * (Math.pow(x2 - x1, 2) * (f2 - f3) - Math.pow(x2 - x3, 2) * (f2 - f1)) /
                ((x2 - x1) * (f2 - f3) - (x2 - x3) * (f2 - f1));
    }

    @Override
    public double count() {
        double a, c, x, w, v, fx, fw, fv, d, e, g, u, fu;
        a = leftBound;
        c = rightBound;
        x = w = v = a + k * (c - a);
        fx = fw = fv = Method.f(x);
        d = e = c - a;
        double tol;
        while (Math.abs(c - a) > EPS) {
            g = e;
            e = d;
            tol = e * Math.abs(x) + EPS / 10;
            if (Math.abs(x - (a + c) / 2) + (c - a) / 2 <= 2 * tol) {
                break;
            }
            if (areDifferent(x, w, v) && areDifferent(fx, fw, fv)) {
                u = parabola(x, w, v, fx, fw, fv);
                if (u >= a && u <= c && Math.abs(u - x) < g / 2) {
                    if ((u - a) < 2 * tol || (c - u) < 2 * tol) {
                        u = x - Math.signum(x - (a + c) / 2) * tol;
                    }
                }
            } else {
                if (x < (a + c) / 2) {
                    u = x + k * (c - x);
                    e = c - x;
                } else {
                    u = x - k * (x - a);
                    e = x - a;
                }
            }
            if (Math.abs(u - x) < tol) {
                u = x + Math.signum(u - x) * tol;
            }
            d = Math.abs(u - x);
            fu = Method.f(u);
            if (fu <= fx) {
                if (u >= x) {
                    a = x;
                } else {
                    c = x;
                }
                v = w;
                w = x;
                x = u;
                fv = fw;
                fw = fx;
                fx = fu;
            } else {
                if (u >= x) {
                    c = u;
                } else {
                    a = u;
                }
                if (fu <= fw || w == x) {
                    v = w;
                    w = u;
                    fv = fw;
                    fw = fu;
                } else if (fu <= fv || v == x || v == w) {
                    v = u;
                    fv = fu;
                }
            }
        }
        return x;
    }
}
