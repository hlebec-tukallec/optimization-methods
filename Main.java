public class Main {
    final static double EPS = Math.exp(-10);   //??
    final static double PHI = (1 + Math.sqrt(5)) / 2;
    final static double RESPHI = 2 - PHI;

    public static void main(String[] args) {
        System.out.println(dichotomy());
        System.out.println(gold());
    }

    //6. f(x) = −5x^5 + 4x^4 − 12x^3 + 11x^2 − 2x + 1 → min на интервале [-0.5; 0.5]

    private static double f(double x) {
        return -5 * Math.pow(x, 5) + 4 * Math.pow(x, 4) - 12 * Math.pow(x, 3) + 11 * Math.pow(x, 2) - 2 * x + 1;
    }

    public static double dichotomy() {
        double a, b, c;
        a = -0.5;
        b = 0.5;
        while (Math.abs(b - a) > EPS) {
            c = (a + b) / 2;
            if (f(b) * f(c) < 0) {
                a = c;
            } else {
                b = c;
            }
        }
        return (a + b) / 2;
    }

    public static double gold() {
        double a, b, x1, x2;
        a = -0.5;
        b = 0.5;
        x1 = a + RESPHI * (b - a);
        x2 = b - RESPHI * (b - a);
        double f1, f2;
        f1 = f(x1);
        f2 = f(x2);
        do {
            if (f1 < f2) {
                b = x2;
                x2 = x1;
                f2 = f1;
                x1 = a + RESPHI * (b - a);
                f1 = f(x1);
            } else {
                a = x1;
                x1 = x2;
                f1 = f2;
                x2 = b - RESPHI * (b - a);
                f2 = f(x2);
            }
        } while (Math.abs(b - a) > EPS);
        return (x1 + x2) / 2;
    }
}
