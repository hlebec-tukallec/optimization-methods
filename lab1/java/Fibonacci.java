public class Fibonacci implements Method {
    private final double PHI = (1 + Math.sqrt(5)) / 2;
    private final double ANTI_PHI = (1 - Math.sqrt(5)) / 2;
    private long n = 0;

    private void countN() {
        while ((rightBound - leftBound) / EPS >= fib(n + 2)) {
            n += 1;
        }
    }

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
        double a = leftBound, b = rightBound, x1, x2, f1, f2;
        countN();
        x1 = a + fib(n - 2) / fib(n) * (b - a);
        x2 = a + fib(n - 1) / fib(n) * (b - a);
        f1 = Method.f(x1);
        f2 = Method.f(x2);
        for (int i = 1; i <= n - 2; i++) {
            if (f1 > f2) {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = findPlace(a, b, i, false);
                f2 = Method.f(x2);
            } else {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = findPlace(a, b, i, true);
                f1 = Method.f(x1);
            }
        }
        return (a + b) / 2;
    }
}
