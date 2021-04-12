public class GradDown extends CommonDown {
    public GradDown(Source source) {
        super(source);
    }

    @Override
    public double calculateLambda(Point grady, Point tmp, double lambda) {
        return lambda / 2;
    }
}
