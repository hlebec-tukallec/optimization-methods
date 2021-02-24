public class Dichotomy implements Method {

    private double count() {
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
        return (a + b) / 2;
    }

    @Override
    public double print() {
        return count();
    }
}
