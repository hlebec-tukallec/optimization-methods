package utils;

import java.util.function.Function;
import static java.lang.Math.pow;

public class Source {
    private int mod = 1;
    private Data[] data;

    public Source() {
        initialise();
    }

    public ExtendedFunction getFunction() {
        return data[mod].function;
    }

    public Point getPoint() {
        return data[mod].point;
    }

    public void changeMod(int mod) {
        this.mod = mod;
    }

    public double getFValue(Point x) {
        return data[mod].function.getFValue(x);
    }

    public double[] getGradientValue(Point x) {
        return data[mod].function.getGradientValue(x);
    }

    public double[][] getHessianValue(Point x) {
        return data[mod].function.getHessianValue(x);
    }

    private static class Data {
        private final ExtendedFunction function;
        private final Point point;

        public Data(ExtendedFunction function, Point point) {
            this.function = function;
            this.point = point;
        }
    }

    private void initialise() {
        Function<Point, Double> f = x -> (x.get(0) * x.get(0) +
                x.get(1) * x.get(1) - 1.2 * x.get(0) * x.get(1));
        Function<Point, double[]> gradF = x -> new double[]{
                (2 * x.get(0) - 1.2 * x.get(1)),
                (2 * x.get(1) - 1.2 * x.get(0))};
        Function<Point, double[][]> hesF = x -> new double[][]{
                new double[]{2.0, -1.2},
                new double[]{-1.2, 2.0}
        };
        Function<Point, Double> f2 = x -> (100 * pow(x.get(0), 4) +
                100 * pow(x.get(1), 2) - 200 * pow(x.get(0), 2) * x.get(1) +
                pow(x.get(0), 2) - 2 * x.get(0) + 1);
        Function<Point, double[]> gradF2 = x -> new double[]{
                400 * pow(x.get(0), 3) - 400 * x.get(0) * x.get(1) + 2 * x.get(0) - 2,
                200 * x.get(1) - 200 * pow(x.get(0), 2)
        };
        Function<Point, double[][]> hesF2 = x -> new double[][]{
                new double[]{1200 * pow(x.get(0), 2) - 400 * x.get(1) + 2, -400 * x.get(0)},
                new double[]{-400 * x.get(0), 200.0}
        };

        this.data = new Data[]{
                new Data(new ExtendedFunction(f, gradF, hesF),
                        new Point(new double[]{4, 1})),
                new Data(new ExtendedFunction(f2, gradF2, hesF2),
                        new Point(new double[]{-1.2, 1}))
        };
    }
}