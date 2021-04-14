public class Main {

    public static void main(String[] args) {
        double[][] A = {{2, 0}, {0, 2}};
        double[] B = {0, 0};
        double C = 0;
        Point p = new Point(new double[]{-1, 1});

        Source source = new Source(A, B, C, p);
        Method gradDown = new GradDown(source);
        gradDown.findMinimum();
        Method fastDown = new FastDown(source);
        fastDown.findMinimum();
    }
}
