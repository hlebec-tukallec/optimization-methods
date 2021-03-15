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
        u = x = w = v = a + k * (c - a);
        fx = fw = fv = Method.f(x);
        d = e = c - a;
        boolean isParabola;
        while (d > EPS) {
            isParabola = false;
            g = e;
            e = d;
            if (areDifferent(x, w, v) && areDifferent(fx, fw, fv)) { //предполагаем метод парабол
                u = parabola(x, w, v, fx, fw, fv);
                if (u >= a + EPS && u <= c - EPS && Math.abs(u - x) < g / 2) { //метод парабол оптимален
                    d = Math.abs(u - x);
                    isParabola = true;
                }
            }
            if (!isParabola) { // золотое сечение
                if (x < (c - a) / 2) {
                    u = x + k * (c - x);
                    d = c - x;
                } else {
                    u = x - k * (x - a);
                    d = x - a;
                }
                if (Math.abs(u - x) < EPS) {
                    u = x + Math.signum(u - x) * EPS;
                }
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
