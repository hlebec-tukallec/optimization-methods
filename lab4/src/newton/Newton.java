package newton;

import utils.ExtendedFunction;
import utils.Method;
import utils.Point;

import java.util.Arrays;

public class Newton implements Method {
    private int iter = 0;
    @Override
    public Point minimum(final ExtendedFunction f, final Point x0, final double eps) {
        iter = 1;
        System.out.println(x0);
        Point x = new Point(x0);
        Point p = slay(f.getHessianValue(x), Arrays.stream(f.getGradientValue(x)).map(y -> -y).toArray());
        while (norm(p) >= eps) {
            iter++;
            x.plus(p);
            p = slay(f.getHessianValue(x), Arrays.stream(f.getGradientValue(x)).map(y -> -y).toArray());
            System.out.println(x);
        }
        return x;
    }

    @Override
    public int getCountOfIterations() {
        return iter;
    }
}
