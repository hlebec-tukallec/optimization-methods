package newton;

import utils.ExtendedFunction;
import utils.Method;
import utils.Point;

import java.util.Arrays;

public class NewtonDirectionDescent implements Method {
    private int iter = 0;
    @Override
    public Point minimum(final ExtendedFunction f, final Point x0, final double eps) {
        iter = 1;
        Point x = new Point(x0);
        System.out.println(x);
        Point d = new Point(Arrays.stream(f.getGradientValue(x)).map(a -> -a).toArray());
        double r = countLambda(f, x, d);
//        System.out.println("odnomernoe znachenie" + r);
        Point s = Point.multiplyOnScalar(d, r);
        x.plus(s);
        System.out.println(x);
        do {
            iter++;
            Point g = new Point(f.getGradientValue(x));
            s = slay(f.getHessianValue(x), Arrays.stream(new Point(g.coordinates).coordinates).map(y -> -y).toArray());
            if (multiplyPoints(s, g) < 0) {
                d = s;
            } else {
                d = Point.negative(g);
            }
            r = countLambda(f, x, d);
//            System.out.println("odnomernoe znachenie" + r);
            s = Point.multiplyOnScalar(d, r);
            x.plus(s);
            System.out.println(x);
        } while (norm(s) >= eps);
        return x;
    }


    @Override
    public int getCountOfIterations() {
        return iter;
    }
}
