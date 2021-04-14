public class ConjugateGradientMethod implements Method {
    private final Source source;

    public ConjugateGradientMethod(Source source) {
        this.source = source;
    }

    @Override
    public Point count() {
        Point curX = source.point, curGradient = source.getGradient(curX);
        Point curP = new Point(MatrixOperation.multiply(curGradient.getCoordinates(),
                -1D));

        do {
            double betta = 0;
            for (int i = 1; i < source.N ; i++) {
                double alfa = Math.pow(getMod(source.getGradient(curX)), 2) /
                        MatrixOperation.multiply(MatrixOperation.multiply(source.A, curP.getCoordinates()), curP.getCoordinates());
                /**/Point newX = countNewPoint(curX, curP, alfa);
                Point newGradient = countNewPoint(curGradient,
                        new Point(MatrixOperation.multiply(source.A, curP.getCoordinates()))
                        , alfa);
                Point newP = new Point(MatrixOperation.add(
                        MatrixOperation.multiply(newGradient.getCoordinates(), -1D),
                        MatrixOperation.multiply(curP.getCoordinates(), betta)));
                betta = Math.pow(getMod(source.getGradient(newX)), 2) /
                        Math.pow(getMod(source.getGradient(curX)), 2);
                curX = newX;
                curP = newP;
                curGradient = newGradient;
            }
        } while (getMod(curGradient) > EPS);
        return curX;
    }
}
