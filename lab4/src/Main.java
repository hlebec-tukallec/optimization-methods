public class Main {
    static class Data {
        private MyFunction function;
        private Point point;

        public Data(MyFunction function, Point point) {
            this.function = function;
            this.point = point;
        }
    }

    //    Data[] data = new Data[3];
    Data[] data = new Data[]{
            new Data(new MyFunction(
                    (Point x) -> (x.get(0) * x.get(0) +
                            x.get(1) * x.get(1) -
                            1.2 * x.get(0) * x.get(1)),
                    (Point x) -> new Double[]{
                            (2 * x.get(0) -
                                    1.2 * x.get(1)),
                            (2 * x.get(1) -
                                    1.2 * x.get(0))
                    },
                    (Point x) -> new Double[][]{
                            new Double[]{2.0, -1.2},
                            new Double[]{-1.2, 2.0}
                    }),
                    new Point(new double[]{4, 1})
            ),
            new Data(new MyFunction(
                    (Point x) -> (100 * pow(x.get(0), 4) * x.get(0) +
                            x.get(1) * x.get(1) -
                            1.2 * x.get(0) * x.get(1)),
                    (Point x) -> new Double[]{
                            (2 * x.get(0) -
                                    1.2 * x.get(1)),
                            (2 * x.get(1) -
                                    1.2 * x.get(0))
                    },
                    (Point x) -> new Double[][]{
                            new Double[]{2.0, -1.2},
                            new Double[]{-1.2, 2.0}
                    }),
                    new Point(new double[]{4, 1})
            )
    };

    private double pow(final double a, final int i) {
        int res = 1;
            while (i > 0) {
                if (i & 1)
                    res *= a;
                a *= a;
                i >>= 1;
            }
            return res;
    }
