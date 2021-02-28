public interface Method {
    double EPS = Math.exp(-25);
    double leftBound = -0.5;
    double rightBound = 0.5;

    static double f(double x) {
        return -5 * Math.pow(x, 5) + 4 * Math.pow(x, 4) - 12 * Math.pow(x, 3) + 11 * Math.pow(x, 2) - 2 * x + 1;
    }

    double count();
}
