import java.util.function.Function;

public class MyFunction {
    private final Function<MyPoint, Double> f;
    private final Function<MyPoint, double[]> gradientF;
    private final Function<MyPoint, double[][]> hessianF;

    public MyFunction(Function<MyPoint, Double> f,
                      Function<MyPoint, double[]> gradientF,
                      Function<MyPoint, double[][]> hessianF) {
        this.f = f;
        this.gradientF = gradientF;
        this.hessianF = hessianF;
    }


    public Function<MyPoint, Double> getF() {
        return f;
    }

    public Function<MyPoint, double[]> getGradientF() {
        return gradientF;
    }

    public Function<MyPoint, double[][]> getHessianF() {
        return hessianF;
    }

    public Double getFValue(MyPoint x) {
        return f.apply(x);
    }

    public double[] getGradientValue(MyPoint x) {
        return gradientF.apply(x);
    }

    public double[][] getHessianValue(MyPoint x) {
        return hessianF.apply(x);
    }
}
