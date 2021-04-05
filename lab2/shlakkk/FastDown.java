import java.util.function.Function;

public class FastDown extends CommonDown {
    public FastDown(Function<Points, Double> method) {
        super(method);
    }

    public FastDown(String method) {
        switch (method) {
            case ("Dichotomy") -> new FastDown(Dichotomy);
            case ("Parabola") -> new FastDown(Parabola);
            default -> new FastDown(Golden);
        }
    }

    private static final Function<Points, Double> Golden = args -> {
        Point grad = args.x;
        Point p = args.y;
        final double phi = 1.6180339887;
        double x1, x2, y1, y2;
        double l = 0;
        double r = 0.05;

        x1 = r - ((r - l) / phi);
        x2 = l + ((r - l) / phi);
        y1 = simplify(x1, grad, p);
        y2 = simplify(x2, grad, p);

        while (Math.abs(r - l) > EPS) {
            if (y1 <= y2) {
                r = x2;
                x2 = x1;
                x1 = r - ((r - l) / phi);
                y2 = y1;
                y1 = simplify(x1, grad, p);
            } else {
                l = x1;
                x1 = x2;
                x2 = l + ((r - l) / phi);
                y1 = y2;
                y2 = simplify(x2, grad, p);
            }
        }
        return (r + l) / 2;
    };

    private static final Function<Points, Double> Dichotomy = args -> {

        return 0.0;
    };

    private static final Function<Points, Double> Parabola = args -> {
        return 0.0;
    };
}
