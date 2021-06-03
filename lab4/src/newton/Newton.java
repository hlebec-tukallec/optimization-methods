package newton;

import utils.ExtendedFunction;
import utils.Method;
import utils.Point;

import java.util.Arrays;

public class Newton implements Method {
    private int iter = 0;
    @Override
    public Point minimum(final ExtendedFunction f, final Point x0, final double eps) {
        iter = 0;
        System.out.println(x0);
        Point p, x = new Point(x0);
        do {
            iter++;
            p = slay(f.getHessianValue(x), Arrays.stream(f.getGradientValue(x)).map(y -> -y).toArray());
            x.plus(p);
            System.out.println(x);
        } while (norm(p) >= eps);
        return x;
    }

    @Override
    public int getCountOfIterations() {
        return iter;
    }
}
