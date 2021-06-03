package newton;

import utils.ExtendedFunction;
import utils.Method;
import utils.Point;

import java.util.Arrays;

public class NewtonOptimisation implements Method {
    @Override
    public Point minimum(final ExtendedFunction f, final Point x0, final double eps) {
        Point d, s, x = new Point(x0);
        do {
            d = slay(f.getHessianValue(x), Arrays.stream(f.getGradientValue(x)).map(y -> -y).toArray());
            double r = countLambda(f, x, d);
            s = Point.multiplyOnScalar(d, r);
            x.plus(s);
        } while (norm(s) >= eps);
        return x;
    }
}
