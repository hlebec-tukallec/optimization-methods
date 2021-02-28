public class Brent implements Method {

    private boolean areDifferent(double a, double b, double c) {
        return a != b && a != c && b != c;
    }

    private double parabola(double x1, double x2, double x3, double f1, double f2, double f3) {
        return x2 - 0.5 * (Math.pow(x2 - x1, 2) * (f2 - f3) - Math.pow(x2 - x3, 2) * (f2 - f1)) /
                ((x2 - x1) * (f2 - f3) - (x2 - x3) * (f2 - f1));
    }

    private double count() {
        double a, c, x, w, v, k, fx, fw, fv, d, e, g, u, fu;
        a = A;
        c = B;
        x = w = v = (a + c) / 2;
        k = (3 - Math.sqrt(5)) / 2;
        fx = fw = fv = Method.f(x);
        d = e = c - a;
        u = -10000000;

        while (c - a > EPS) {
            g = e;
            e = d;

            if (areDifferent(x, w, v) && areDifferent(fx, fw, fv)) {
                u = parabola(x, w, v, fx, fw, fv);
            }
            if (u != 10000000 && u >= a + EPS && u <= c - EPS && Math.abs(u - x) < g / 2) {
                d = Math.abs(u - x);
            } else {
                if (x < (a + c) / 2) {
                    u = x + k * (c - x); //golen section [x, c]
                    d = c - x;
                } else {
                    u = x - k * (x - a); //golden section [a, x]
                    d = x - a;
                }
                if (Math.abs(u - x) < EPS) {
                    u = u - x >= 0 ? x + EPS : x - EPS;
                }
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
                        c = x;
                    } else {
                        a = x;
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
        }
        return x;

    }

    @Override
    public double print() {
        return count();
    }
}
