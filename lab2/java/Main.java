import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        double[][] A1 = {{20, 0}, {0, 20}};
//        double[] B1 = {0, 0};
//        double C1 = 0;
//
//        double[][] A2 = {{128, 126}, {126, 128}};
//        double[] B2 = {-10, 30};
//        double C2 = 13;
        for (int i = 10; i <= 10000; i += 50) {
            double[][] A = new double[i][i];
            for (int j = 0; j < i; j++) {
                A[j][j] = 2;
            }
            A[0][0] = 2;
            double[] B = new double[i];
            double C = 0;
            double[] poi = new double[i];
            Arrays.fill(poi, 50);
            Point p = new Point(poi);
            Source source = new Source(A, B, C, p);
            GradientDescentMethod gradDown = new GradientDescentMethod(source);
            gradDown.count();
            SteepestDescentMethod fastDown = new SteepestDescentMethod(source);
            fastDown.count();
            ConjugateGradientMethod conjGrad = new ConjugateGradientMethod(source);
            conjGrad.count();
            System.out.println(gradDown.iter + " & " + fastDown.iter + " & " + conjGrad.iter + " & " +
                    i + " & " + 1);
        }
/*

        int n = 100;
        for (int k = 10; k <= 10000; k += 50) {
            double[][] A = new double[n][n];
            for (int j = 0; j < n; j++) {
                A[j][j] = 2;
            }
            A[0][0] = 2 * k;
            double[] B = new double[n];
            double C = 0;
            double[] poi = new double[n];
            Arrays.fill(poi, 50);
            Point p = new Point(poi);
            Source source = new Source(A, B, C, p);
            GradientDescentMethod gradDown = new GradientDescentMethod(source);
            gradDown.count();
            SteepestDescentMethod fastDown = new SteepestDescentMethod(source);
            fastDown.count();
            ConjugateGradientMethod conjGrad = new ConjugateGradientMethod(source);
            conjGrad.count();
            System.out.println(gradDown.iter + " & " + fastDown.iter + " & " + conjGrad.iter + " & " +
                    k + " & " + 100 + "\\\\");
        }*/
    }
}
