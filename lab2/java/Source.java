public class Source {
    public final double[][] A;
    public final double[] B;
    public final double C;
    public final Point point;  //выбрать
    public final int N;
    public Source(double[][] a, double[] b, double c, Point point) {
        A = a;
        B = b;
        this.C = c;
        this.point = point;
        N = a.length*1000;
    }

    public double getFunctionValue(Point x) {
        return 0.5 * MatrixOperation.multiply(
                MatrixOperation.multiply(x.getCoordinates(), A),
                x.getCoordinates())
                + MatrixOperation.multiply(B, x.getCoordinates())
                + C;
    }

    public Point getGradient(Point x) {
        return new Point(MatrixOperation.add(MatrixOperation.multiply(x.getCoordinates(), A), B));
    }

//    Function<Point, Double> f1 = point -> 10 * (point.x * point.x + point.y * point.y);
//    Function<Point, Point> f1Grad = point -> new Point(20 * point.x, 20 * point.y);
//
//
//    Function<Point, Double> f2 = point ->
//            (64 * point.x * point.x + 126 * point.x * point.y +
//                    64 * point.y * point.y - 10 * point.x + 30 * point.y + 13);
//
//    Function<Point, Point> f2Grad = point -> new Point(128 * point.x + 126 * point.y - 10,
//            126 * point.x + 128 * point.y + 30);

}
