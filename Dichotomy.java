public class Dichotomy implements Method {
    private double res;
    double delta = EPS / 4;

    private void count() {
        double a, b, x1, x2;
        a = A;
        b = B;
        while (Math.abs(b - a) > EPS) {

            x1 = (a + b) / 2 - delta;
            x2 = (a + b) / 2 + delta;

            if (Method.f(x1) > Method.f(x2)) {
                a = x1;
            } else {
                b = x2;
            }
        }
        res = (a + b) / 2;
    }

    @Override
    public double print() {
        count();
        return res;
    }
}
