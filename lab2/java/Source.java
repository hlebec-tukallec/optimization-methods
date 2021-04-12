import java.util.function.Function;

public class Source {
    public final Function<Point, Double> func;
    public final Function<Point, Point> gradient;
    public final Point point;  //выбрать
    public final double EPS;


    public Source(String mod, Point A, double EPS) {
        switch (mod) {
            case "1" -> {
                this.func = f1;
                this.gradient = f1Grad;
            }
            case "2" -> {
                this.func = f2;
                this.gradient = f2Grad;
            }
            default -> {
                this.func = point -> point.x;
                this.gradient = point -> new Point(1, 0);
            }
        }
        this.point = A;
        this.EPS = EPS;
    }

    Function<Point, Double> f1 = point -> 10 * (point.x * point.x + point.y * point.y);
    Function<Point, Point> f1Grad = point -> new Point(20 * point.x, 20 * point.y);


    Function<Point, Double> f2 = point ->
            10 * (64 * point.x * point.x + 126 * point.x * point.y +
                    64 * point.y * point.y - 10 * point.x + 30 * point.y + 13);

    Function<Point, Point> f2Grad = point -> new Point(128 * point.x + 126 * point.y - 10,
            126 * point.x + 128 * point.y + 30);

}
