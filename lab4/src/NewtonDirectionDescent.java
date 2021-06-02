import java.util.Arrays;

public class NewtonDirectionDescent implements Method {
    @Override
    public MyPoint minimum(final MySource f, final MyPoint x0, final double eps) {
        MyPoint x = new MyPoint(x0);
        MyPoint d = new MyPoint(Arrays.stream(f.getGradientValue(x)).map(a -> -a).toArray());
        double r = countLambda(f, x, d);
        MyPoint s = MyPoint.multiplyOnScalar(d, r);
        x.plus(s);
        do {
            MyPoint g = new MyPoint(f.getGradientValue(x));
            s = slay(f.getHessianValue(x), g.coordinates);
            if (multiplyPoints(s, g) < 0) {
                d = s;
            } else {
                d = MyPoint.negative(g);
            }
            r = countLambda(f, x, d);
            s = MyPoint.multiplyOnScalar(d, r);
            x.plus(s);
        } while (norm(s) >= eps);
        return x;
    }
}
