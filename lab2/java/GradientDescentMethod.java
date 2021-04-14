public class GradientDescentMethod implements Method {
    private final Source source;
    public int iter = 0;

    public GradientDescentMethod(Source source) {
        this.source = source;
    }

    @Override
    public Point count() {
        Point cur = source.point, gradient;
        double curValue = source.getFunctionValue(cur);
        double lambda = 0.01;
        do {
            gradient = source.getGradient(cur);
            while (true) {
                Point next = countNewPoint(cur, gradient, lambda);
                double nextValue = source.getFunctionValue(next);
                if (nextValue < curValue) {
                    cur = next;
                    iter++;
                    curValue = nextValue;
                    break;
                } else {
                    lambda = lambda / 2;
                }
            }
        } while (getMod(gradient) >= EPS);
        return cur;
    }
}
