public class Newton implements Method {
    @Override
    public MyPoint minimum(final MySource f, final MyPoint x0, final double eps) {
        MyPoint p, x = new MyPoint(x0);
        do {
            p = slay(f.getHessianValue(x), f.getGradientValue(x));
            x.plus(p);
        } while (norm(p) >= eps);
        return x;
    }
}
