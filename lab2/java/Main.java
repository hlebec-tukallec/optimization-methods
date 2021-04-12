public class Main {
    private static final Point point = new Point(-1, 1);
    private static final double EPS = 0.000001;
    private static final String mod = "1";


    public static void main(String[] args) {
        Source source = new Source(mod, point, EPS);
        Method gradDown = new GradDown(source);
        gradDown.count();
        Method fastDown = new FastDown(source);
        fastDown.count();
    }
}
