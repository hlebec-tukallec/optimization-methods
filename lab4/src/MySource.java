import java.util.function.Function;

public class MySource {
    private int mod = 1;
    private Data[] data;

    public MySource() {
        initialise();
    }

    public MySource(int mod) {
        this.mod = mod;
    }

    public double getFValue(MyPoint x) {
        return data[mod].function.getFValue(x);
    }

    public double[] getGradientValue(MyPoint x) {
        return data[mod].function.getGradientValue(x);
    }

    public double[][] getHessianValue(MyPoint x) {
        return data[mod].function.getHessianValue(x);
    }

    private class Data {
        private final MyFunction function;
        private final MyPoint point;

        public Data(MyFunction function, MyPoint point) {
            this.function = function;
            this.point = point;
        }

        public MyFunction getFunction() {
            return function;
        }

        public MyPoint getPoint() {
            return point;
        }
    }

    private void initialise() {
        Function<MyPoint, Double> f = x -> (x.get(0) * x.get(0) +
                x.get(1) * x.get(1) - 1.2 * x.get(0) * x.get(1));
        Function<MyPoint, double[]> gradF = x -> new double[]{
                (2 * x.get(0) - 1.2 * x.get(1)),
                (2 * x.get(1) - 1.2 * x.get(0))};
        Function<MyPoint, double[][]> hesF = x -> new double[][]{
                new double[]{2.0, -1.2},
                new double[]{-1.2, 2.0}
        };
        Function<MyPoint, Double> f2 = x -> (100 * pow(x.get(0), 4) +
                100 * pow(x.get(1), 2) - 200 * pow(x.get(0), 2) * x.get(1) +
                pow(x.get(0), 2) - 2 * x.get(0) + 1);
        Function<MyPoint, double[]> gradF2 = x -> new double[]{
                400 * pow(x.get(0), 3) - 400 * x.get(0) * x.get(1) + 2 * x.get(0) - 2,
                200 * x.get(1) - 200 * pow(x.get(0), 2)
        };
        Function<MyPoint, double[][]> hesF2 = x -> new double[][]{
                new double[]{1200 * pow(x.get(0), 2) - 400 * x.get(1) + 2, -400 * x.get(0)},
                new double[]{-400 * x.get(0), 200.0}
        };

        this.data = new Data[]{
                new Data(new MyFunction(f, gradF, hesF),
                        new MyPoint(new double[]{4, 1})),
                new Data(new MyFunction(f2, gradF2, hesF2),
                        new MyPoint(new double[]{-1.2, 1}))
        };
    }


    private static double pow(final double a, final int n) {
        if (n == 0)
            return 1;
        if (n % 2 == 1)
            return pow(a, n - 1) * a;
        else {
            double b = pow(a, n / 2);
            return b * b;
        }
    }
}