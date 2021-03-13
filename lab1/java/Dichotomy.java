public class Dichotomy implements Method {
    private final double DELTA = EPS / 4;

    @Override
    public double count() {
        double a, b, x1, x2;
        a = leftBound;
        b = rightBound;

        while (Math.abs(b - a) > EPS) {

            x1 = (a + b) / 2 - DELTA;
            x2 = (a + b) / 2 + DELTA;


            if (Method.f(x1) > Method.f(x2)) {
                a = x1;
            } else {
                b = x2;
            }
        }
        return (a + b) / 2;
    }
}


