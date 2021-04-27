public class Source {
    public final double[][] A;
    public final double[] B;
    public final double C;
    public final Point point;
    public final int N;

    public Source(double[][] a, double[] b, double c, Point point) {
        A = a;
        B = b;
        this.C = c;
        this.point = point;
        N = a.length;
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
}
