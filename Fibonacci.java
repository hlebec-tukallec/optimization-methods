public class Fibonacci implements Method {
    private double res;
    private final long n = 30;

    private double fib(long x) {
        return (1 / Math.sqrt(5)) * (Math.pow(PHI, x) - Math.pow(ANTIPHI, x));
    }

    double findPlace(double a, double b, int k, boolean isFirst) {
        if (isFirst) {
            return a + (fib(n - k - 2) * (b - a)) / fib(n - k);
        } else {
            return a + (fib(n - k - 1) * (b - a)) / fib(n - k);
        }
    }

    private void count() {
        double x1, x2, a, b, f1, f2;

        a = A;
        b = B;
        x1 = a + fib(n - 2) / fib(n) * (b - a);
        x2 = a + fib(n - 1) / fib(n) * (b - a);
        f1 = Method.f(x1);
        f2 = Method.f(x2);

        for (int i = 1; i <= n; i++) {
            if (f1 > f2) {
                a = x1;
                x1 = x2;
                x2 = findPlace(a, b, i, false);
                f1 = Method.f(x1);
                f2 = Method.f(x2);
            } else {
                b = x2;
                x2 = x1;
                x1 = findPlace(a, b, i, true);
                f1 = Method.f(x1);
                f2 = Method.f(x2);
            }
            if (i == n - 2) {
                break;
            }
        }
        res =(a + b) / 2;
    }

    @Override
    public double print() {
        count();
        return res;
    }
}
