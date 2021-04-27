public class ConjugateGradientMethod implements Method {
    private final Source source;

    public ConjugateGradientMethod(Source source) {
        this.source = source;
    }

    private Point countNew(Point p, Point grad, double lambda) {
        int n = p.getCoordinates().length;
        double[] coordinates = new double[n];
        for (int i = 0; i < n; i++) {
            coordinates[i] = p.getCoordinates()[i] + lambda * grad.getCoordinates()[i];
        }
        return new Point(coordinates);
    }

    @Override
    public Point count() {
        Point curX = source.point, curGradient = source.getGradient(curX);
        Point curP = new Point(MatrixOperation.multiply(curGradient.getCoordinates(), -1D));
        do {
            for (int i = 0; i < source.N && getMod(curGradient) > EPS; i++) {
                double[] curApK = MatrixOperation.multiply(source.A, curP.getCoordinates());
                double alfa = Math.pow(getMod(source.getGradient(curX)), 2) /
                        MatrixOperation.multiply(curApK, curP.getCoordinates());
                Point newX = countNew(curX, curP, alfa);
                Point newGradient = countNew(curGradient,
                        new Point(curApK)
                        , alfa);
                double betta = (i == 0) ? 0 : Math.pow(getMod(source.getGradient(newX)), 2) /
                        Math.pow(getMod(source.getGradient(curX)), 2);
                Point newP = new Point(MatrixOperation.add(
                        MatrixOperation.multiply(newGradient.getCoordinates(), -1D),
                        MatrixOperation.multiply(curP.getCoordinates(), betta)));

                curX = newX;
                curP = newP;
                curGradient = newGradient;
            }
        } while (getMod(curGradient) > EPS);
        return curX;
    }
}
