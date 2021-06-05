package utils;

import java.util.function.Function;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

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

        Function<Point, Double> f3 = x -> (pow(pow(x.get(0), 2) + x.get(1) - 11, 2) +
                pow(x.get(0) + pow(x.get(1), 2) - 7, 2));
        Function<Point, double[]> gradF3 = x -> new double[]{
                4 * (pow(x.get(0), 3) + x.get(0) * x.get(1) - 11 * x.get(0)) + 2 * (x.get(0) + pow(x.get(1), 2) - 7),
                2 * (pow(x.get(0), 2) + x.get(1) - 11) + 4 * (x.get(0) * x.get(1) + pow(x.get(1), 3) - 7 * x.get(1))
        };
        Function<Point, double[][]> hesF3 = x -> new double[][]{
                new double[]{12 * pow(x.get(0), 2) + 4 * x.get(1) - 42, 4 * (x.get(0) + x.get(1))},
                new double[]{4 * (x.get(0) + x.get(1)), 4 * x.get(0) + 12 * pow(x.get(1), 2) - 26}
        };

        Function<Point, Double> f4 = x -> (pow(x.get(0) + 10 * x.get(1), 2) + 5 * pow(x.get(2) - x.get(3), 2) +
                pow(x.get(1) - 2 * x.get(2), 4) + 10 * pow(x.get(0) - x.get(3), 4));

        Function<Point, double[]> gradF4 = x -> new double[]{
                2 * (20 * pow(x.get(0) - x.get(3), 3) + x.get(0) + 10 * x.get(1)),
                4 * (5 * (x.get(0) + 10 * x.get(1)) + pow(x.get(1) - 2 * x.get(2), 3)),
                10 * (x.get(2) - x.get(3)) - 8 * pow(x.get(1) - 2 * x.get(2), 3),
                10 * (-4 * pow(x.get(0) - x.get(3), 3) - x.get(2) + x.get(3))
        };
        Function<Point, double[][]> hesF4 = x -> new double[][]{
                new double[]{120 * pow(x.get(0) - x.get(3), 2) + 2, 20, 0, -120 * pow(x.get(0) - x.get(3), 2)},
                new double[]{20, 200 + 12 * pow((x.get(1) - 2 * x.get(2)), 2), -24 * pow(x.get(1) - 2 * x.get(2), 2), 0},
                new double[]{0, -24 * pow(x.get(1) - 2 * x.get(2), 2), 10 + 48 * (x.get(1) - 2 * x.get(2)), -10},
                new double[]{-120 * pow(x.get(0) - x.get(3), 2), 0, -10, 120 * pow(x.get(0) - x.get(3), 2) + 10}
        };

        Function<Point, Double> f5 = x -> (-2.0 / (0.25 * pow(x.get(0) - 1, 2) + 1.0 / 9 * pow(x.get(1) - 1, 2) + 1) - 1.0 / (0.25 * pow(x.get(0) - 2, 2) + 1.0 / 9 * pow(x.get(1) - 1, 2) + 1) + 100);
        Function<Point, double[]> gradF5 = x -> new double[]{
                (648 * (x.get(0) - 2)) / pow(9 * pow(x.get(0), 2) - 36 * x.get(0) + 4 * pow(x.get(1), 2) - 8 * x.get(1) + 76, 2) + (x.get(0) - 1) / pow(0.25 * pow(x.get(0) - 1, 2) + 1.0 / 9 * pow(x.get(1) - 1, 2) + 1, 2),
                2.0 / 9 * (x.get(1) - 1) * (2.0 / pow(0.25 * pow(x.get(0) - 1, 2) + 1.0 / 9 * pow(x.get(1) - 1, 2) + 1, 2) + 1.0 / pow(0.25 * pow(x.get(0) - 2, 2) + 1.0 / 9 * pow(x.get(1) - 1, 2) + 1, 2))
        };
        Function<Point, double[][]> hesF5 = x -> new double[][]{
                new double[]{(-pow(-1 + x.get(0), 2) / pow(1 + 0.25 * pow(-1 + x.get(0), 2) + 1.0 / 9 * pow(-1 + x.get(1), 2), 3) +
                        1.0 / pow(1 + 0.25 * pow(-1 + x.get(0), 2) + 1.0 / 9 * pow(-1 + x.get(1), 2), 2) - (23328 * pow(-2 + x.get(0), 2)) / pow(76 - 36 * x.get(0) + 9 * x.get(0) * x.get(0) -
                        8 * x.get(1) + 4 * x.get(1) * x.get(1), 3) + 648 / pow(76 - 36 * x.get(0) + 9 * x.get(0) * x.get(0) - 8 * x.get(1) + 4 * x.get(1) * x.get(1), 2)),
                        4.0 / 9 * (-1 + x.get(1)) * (-(-1 + x.get(0)) / pow(1 + 1.0 / 4 * pow(-1 + x.get(0), 2) + 1.0 / 9 * pow(-1 + x.get(1), 2), 3) - (23328 * (-2 + x.get(0))) /
                                pow(76 - 36 * x.get(0) + 9 * x.get(0) * x.get(0) - 8 * x.get(1) + 4 * x.get(1) * x.get(1), 3))
                },
                new double[]{4.0 / 9 * (-1 + x.get(1)) * (-(-1 + x.get(0)) / pow(1 + 1.0 / 4 * pow(-1 + x.get(0), 2) + 1.0 / 9 * pow(-1 + x.get(1), 2), 3) - (23328 * (-2 + x.get(0))) /
                        pow(76 - 36 * x.get(0) + 9 * x.get(0) * x.get(0) - 8 * x.get(1) + 4 * x.get(1) * x.get(1), 3)),
                        2.0 / 9 * (4.0 / 9 * pow(x.get(1) - 1, 2) * (-2.0 / pow(1.0 / 4 * pow(x.get(0) - 1, 2) + 1.0 / 9 * pow(x.get(1) - 1, 2) + 1, 3) - 1.0 /
                                pow(1.0 / 4 * pow(x.get(0) - 2, 2) + 1.0 / 9 * pow(x.get(1) - 1, 2) + 1, 3)) + 1.0 / pow(1.0 / 4 * pow(x.get(0) - 2, 2) + 1.0 / 9 * pow(x.get(1) - 1, 2) + 1, 2) +
                                2.0 / pow(1.0 / 4 * pow(x.get(0) - 1, 2) + 1.0 / 9 * pow(x.get(1) - 1, 2) + 1, 2))
                }
        };

        Function<Point, Double> f6 = x -> (3 * pow(x.get(0), 2) + x.get(0) * x.get(1) + 2 * pow(x.get(1), 2) - x.get(0) - 4 * x.get(1));
        Function<Point, double[]> gradF6 = x -> new double[]{
                (6 * x.get(0) + x.get(1) - 1),
                (x.get(0) + 4 * x.get(1) - 4)};
        Function<Point, double[][]> hesF6 = x -> new double[][]{
                new double[]{6, 1},
                new double[]{1, 4}
        };

        Function<Point, Double> f7 = x -> (-x.get(1) * sqrt(x.get(0)) + 2 * pow(x.get(1), 2) + x.get(0) - 14 * x.get(1));
        Function<Point, double[]> gradF7 = x -> new double[]{
                (- x.get(1)) / ((2 * sqrt(x.get(0)))) + 1,
                (-sqrt(x.get(0)) + 4 * x.get(1) - 14)};
        Function<Point, double[][]> hesF7 = x -> new double[][]{
                new double[]{x.get(1) / (4 * x.get(0) * sqrt(x.get(0))), -1 / (2 * sqrt(x.get(0)))},
                new double[]{-1 / (2 * sqrt(x.get(0))), 4}
        };

        this.data = new Data[]{
                new Data(new ExtendedFunction(f, gradF, hesF), new Point(new double[]{4, 1})),
                new Data(new ExtendedFunction(f2, gradF2, hesF2), new Point(new double[]{-1.2, 1})),
                new Data(new ExtendedFunction(f3, gradF3, hesF3), new Point(new double[]{1, 1})),
                new Data(new ExtendedFunction(f4, gradF4, hesF4), new Point(new double[]{1, 1, 1, 1})),
                new Data(new ExtendedFunction(f5, gradF5, hesF5), new Point(new double[]{1, 1})),
                // for 1.1: 3x^2+xy+2y^2-x-4y, min point: (0, 1)
                new Data(new ExtendedFunction(f6, gradF6, hesF6), new Point(new double[]{1, -1})),
//                new Data(new ExtendedFunction(f6, gradF6, hesF6), new Point(new double[]{-15, -5})),
                // for 1.1: -y*sqrt(x)+2*y^2+x-14y, min point: (4, 4)
                new Data(new ExtendedFunction(f7, gradF7, hesF7), new Point(new double[]{1, -1})),
//                new Data(new ExtendedFunction(f7, gradF7, hesF7), new Point(new double[]{3, 5}))
        };
    }
}