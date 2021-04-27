import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        double[][] A1 = {{20, 0}, {0, 20}};
//        double[] B1 = {0, 0};
//        double C1 = 0;
//
        double[][] A2 = {{128, 126}, {126, 128}};
        double[] B2 = {-10, 30};
        double C2 = 13;

        Point p = new Point(new double[]{1, -1});

        Source source = new Source(A2, B2, C2, p);
        GradientDescentMethod gradDown = new GradientDescentMethod(source);
        System.out.println(gradDown.count());
        SteepestDescentMethod fastDown = new SteepestDescentMethod(source);
        System.out.println(fastDown.count());
        ConjugateGradientMethod conjGrad = new ConjugateGradientMethod(source);
        System.out.println(conjGrad.count());
    }
}
