public class Fibonacci implements Method {
    private double PHI = (1 + Math.sqrt(5)) / 2;
    private double ANTI_PHI = (1 - Math.sqrt(5)) / 2;
    private final long n = 30;

    private double fib(long x) {
        return (1 / Math.sqrt(5)) * (Math.pow(PHI, x) - Math.pow(ANTI_PHI, x));
    }

    private double findPlace(double a, double b, int k, boolean isFirst) {
        if (isFirst) {
            return a + (fib(n - k - 2) * (b - a)) / fib(n - k);
        } else {
            return a + (fib(n - k - 1) * (b - a)) / fib(n - k);
        }
    }

    @Override
    public double count() {
        double x1, x2, a, b, f1, f2;

        a = leftBound;
        b = rightBound;
        x1 = a + fib(n - 2) / fib(n) * (b - a);
        x2 = a + fib(n - 1) / fib(n) * (b - a);
        f1 = Method.f(x1);
        f2 = Method.f(x2);

        for (int i = 1; i <= n - 2; i++) {
            if (f1 > f2) {
                a = x1;
                x1 = x2;
                x2 = findPlace(a, b, i, false);
            } else {
                b = x2;
                x2 = x1;
                x1 = findPlace(a, b, i, true);
            }
            f1 = Method.f(x1);
            f2 = Method.f(x2);
        }
        return (a + b) / 2;
    }
}
