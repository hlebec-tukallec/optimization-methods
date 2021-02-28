public interface Method {
    double EPS = Math.exp(-10);
    double DELTA = EPS / 4;
    double PHI = (1 + Math.sqrt(5)) / 2;
    double ANTIPHI = (1 - Math.sqrt(5)) / 2;
    double RESPHI = 2 - PHI;

    double A = -0.5;
    double B = 0.5;

    static double f(double x) {
        return -5 * Math.pow(x, 5) + 4 * Math.pow(x, 4) - 12 * Math.pow(x, 3) + 11 * Math.pow(x, 2) - 2 * x + 1;
    }

    double print();
}
