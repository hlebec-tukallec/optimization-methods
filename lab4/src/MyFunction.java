import java.util.function.Function;

public class MyFunction {
    private final Function<MyPoint, Double> f;
    private final Function<MyPoint, Double[]> gradientF;
    private final Function<MyPoint, Double[][]> hessianF;

    public MyFunction(Function<MyPoint, Double> f,
                      Function<MyPoint, Double[]> gradientF,
                      Function<MyPoint, Double[][]> hessianF) {
        this.f = f;
        this.gradientF = gradientF;
        this.hessianF = hessianF;
    }


    public Function<MyPoint, Double> getF() {
        return f;
    }

    public Function<MyPoint, Double[]> getGradientF() {
        return gradientF;
    }

    public Function<MyPoint, Double[][]> getHessianF() {
        return hessianF;
    }

    public Double getFValue(MyPoint x) {
        return f.apply(x);
    }

    public Double[] getGradientValue(MyPoint x) {
        return gradientF.apply(x);
    }

    public Double[][] getHessianValue(MyPoint x) {
        return hessianF.apply(x);
    }
}
