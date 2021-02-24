public class Gold implements Method {
    private double res;

    private void count() {
        double a, b, x1, x2;
        a = A;
        b = B;
        x1 = a + RESPHI * (b - a);
        x2 = b - RESPHI * (b - a);
        double f1, f2;
        f1 = Method.f(x1);
        f2 = Method.f(x2);
        do {
            if (f1 < f2) {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = a + RESPHI * (b - a);
                f1 = Method.f(x1);
            } else {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = b - RESPHI * (b - a);
                f2 = Method.f(x2);
            }
        } while (Math.abs(b - a) > EPS);
        res = (x1 + x2) / 2;
    }

    @Override
    public double print() {
        count();
        return res;
    }
}
