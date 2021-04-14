public class GradDown implements Method {
    private final Source source;

    public GradDown(Source source) {
        this.source = source;
    }

    private Point gradDownImpl() {
        Point cur = source.point, gradient;
        Double curValue = source.func.apply(cur);

        double lambda = 0.01;
        do {
            gradient = source.gradient.apply(cur);
            while (true) {
                Point next = countNewPoint(cur, gradient, lambda);
                Double nextValue = source.func.apply(next);
                if (nextValue < curValue) {
                    cur = next;
                    curValue = nextValue;
                    break;
                } else {
                    lambda = lambda / 2;
                }
            }
        } while (getMod(gradient) >= source.EPS);
        return cur;
    }

    public void findMinimum() {
        Point ans = gradDownImpl();
        System.out.println(ans.x + " " + ans.y);
    }
}
