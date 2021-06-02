public class NewtonOptimisation implements Method {
    @Override
    public MyPoint minimum(final MySource f, final MyPoint x0, final double eps) {
        MyPoint d, s, x = new MyPoint(x0);
        do {
            d = slay(f.getHessianValue(x), f.getGradientValue(x));
            double r = countLambda(f, x, d);
            s = MyPoint.multiplyOnScalar(d, r);
            x.plus(s);
        } while (norm(s) >= eps);
        return x;
    }
}
