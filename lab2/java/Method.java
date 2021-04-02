public interface Method {

    double EPS = 0.000001;

    class point {
        double x, y;

        public point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }

    static double f(point x) {
        return (x.x * x.x + x.y * x.y);
    } //придумать две-три функции

    point a = new point(-1, 1);  //выбрать

    public void count();
}
