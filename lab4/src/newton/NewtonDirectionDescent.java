package newton;

import utils.ExtendedFunction;
import utils.Point;

import java.util.Arrays;

public class NewtonDirectionDescent implements Method {
    @Override
    public Point minimum(final ExtendedFunction f, final Point x0, final double eps) {
        Point x = new Point(x0);
        Point d = new Point(Arrays.stream(f.getGradientValue(x)).map(a -> -a).toArray());
        double r = countLambda(f, x, d);
        Point s = Point.multiplyOnScalar(d, r);
        x.plus(s);
        do {
            Point g = new Point(f.getGradientValue(x));
            s = slay(f.getHessianValue(x), g.coordinates);
            if (multiplyPoints(s, g) < 0) {
                d = s;
            } else {
                d = Point.negative(g);
            }
            r = countLambda(f, x, d);
            s = Point.multiplyOnScalar(d, r);
            x.plus(s);
        } while (norm(s) >= eps);
        return x;
    }
}
