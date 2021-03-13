public class Gold implements Method {
    private final double PHI = 2 - (1 + Math.sqrt(5)) / 2;

    @Override
    public double count() {
        double a = leftBound, b = rightBound, x1, x2;
        x1 = a + PHI * (b - a);
        x2 = b - PHI * (b - a);
        double f1, f2;
        f1 = Method.f(x1);
        f2 = Method.f(x2);
        while (Math.abs(b - a) > EPS) {

            if (f1 < f2) {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = a + PHI * (b - a);
                f1 = Method.f(x1);
            } else {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = b - PHI * (b - a);
                f2 = Method.f(x2);
            }
        }
        return (x1 + x2) / 2;
    }
}
