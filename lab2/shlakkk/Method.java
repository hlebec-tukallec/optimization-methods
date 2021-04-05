public interface Method {

    double EPS = 0.000001;

    class Point {
        double x, y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    class Points {
        Point x, y;

        public Points(Point x, Point y) {
            this.x = x;
            this.y = y;
        }
    }

    static double f1(Point point) {
        return 10 * (point.x * point.x + point.y * point.y);
    } //придумать две-три функции


    static Point f1Grad(Point point) {
        return new Point(20 * point.x, 20 * point.y);
    }

    static double f2(Point point) {
        return (64 * point.x * point.x + 126 * point.x * point.y + 64 * point.y * point.y
                - 10 * point.x + 30 * point.y + 13);
    }

    static Point f2Grad(Point point) {
        double f2DiffX = 128 * point.x + 126 * point.y - 10;
        double f2DiffY = 126 * point.x + 128 * point.y + 30;
        return new Point(f2DiffX, f2DiffY);
    }

    Point a = new Point(-1, 1);  //выбрать

    void count();
}
