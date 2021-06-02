package utils;

import java.util.function.Function;

public class ExtendedFunction {
    private final Function<Point, Double> f;
    private final Function<Point, double[]> gradientF;
    private final Function<Point, double[][]> hessianF;

    public ExtendedFunction(Function<Point, Double> f,
                            Function<Point, double[]> gradientF,
                            Function<Point, double[][]> hessianF) {
        this.f = f;
        this.gradientF = gradientF;
        this.hessianF = hessianF;
    }


    public Function<Point, Double> getF() {
        return f;
    }

    public Function<Point, double[]> getGradientF() {
        return gradientF;
    }

    public Function<Point, double[][]> getHessianF() {
        return hessianF;
    }

    public Double getFValue(Point x) {
        return f.apply(x);
    }

    public double[] getGradientValue(Point x) {
        return gradientF.apply(x);
    }

    public double[][] getHessianValue(Point x) {
        return hessianF.apply(x);
    }
}
