public class Dichotomy implements Method {
    private double res;

    private void count() {
        double a, b, c;
        a = A;
        b = B;
        while (Math.abs(b - a) > EPS) {
            c = (a + b) / 2;
            if (Method.f(b) * Method.f(c) < 0) {
                a = c;
            } else {
                b = c;
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
