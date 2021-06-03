package newton;

import utils.ExtendedFunction;
import utils.Method;
import utils.Point;

public class Newton implements Method {
    @Override
    public Point minimum(final ExtendedFunction f, final Point x0, final double eps) {
        Point p, x = new Point(x0);
        do {
            p = slay(f.getHessianValue(x), f.getGradientValue(x));
            x.plus(p);
        } while (norm(p) >= eps);
        return x;
    }
}
