import java.util.function.Function;

public class MyFunction {
    private final Function<Point, Double> f;
    private final Function<Point, Double[]> gradientF;
    private final Function<Point, Double[][]> hessianF;

    public MyFunction(final Function<Point, Double> f,
                      final Function<Point, Double[]> gradientF,
                      final Function<Point, Double[][]> hessianF) {
        this.f = f;
        this.gradientF = gradientF;
        this.hessianF = hessianF;
    }

    public Function<Point, Double> getF() {
        return f;
    }

    public Function<Point, Double[]> getGradientF() {
        return gradientF;
    }

    public Function<Point, Double[][]> getHessianF() {
        return hessianF;
    }

    public Double getFValue(Point x) {
        return f.apply(x);
    }

    public Double[] getGradientValue(Point x) {
        return gradientF.apply(x);
    }

    public Double[][] getHessianValue(Point x) {
        return hessianF.apply(x);
    }
}
