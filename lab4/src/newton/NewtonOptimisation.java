package newton;

import utils.ExtendedFunction;
import utils.Method;
import utils.Point;

import java.util.Arrays;

public class NewtonOptimisation implements Method {
    private int iter;
    @Override
    public Point minimum(final ExtendedFunction f, final Point x0, final double eps) {
        iter = 0;
        Point d, s, x = new Point(x0);
        do {
            System.out.println(x);
            iter++;
            d = slay(f.getHessianValue(x), Arrays.stream(f.getGradientValue(x)).map(y -> -y).toArray());
            double r = countLambda(f, x, d);
            s = Point.multiplyOnScalar(d, r);
            x.plus(s);
        } while (norm(s) >= eps);
        return x;
    }

    @Override
    public int getCountOfIterations() {
        return iter;
    }
}
