public class Main {

    public static void main(String[] args) {
        double[][] A1 = {{20, 0}, {0, 20}};
        double[] B1 = {0, 0};
        double C1 = 0;

        double[][] A2 = {{128, 126}, {126, 128}};
        double[] B2 = {-10, 30};
        double C2 = 13;

        Point p = new Point(new double[]{-1, 1});

        Source source = new Source(A1, B1, C1, p);
        Method gradDown = new GradientDescentMethod(source);
        gradDown.findMinimum();
        Method fastDown = new SteepestDescentMethod(source);
        fastDown.findMinimum();
        Method conjGrad = new ConjugateGradientMethod(source);
        conjGrad.findMinimum();
    }
}
