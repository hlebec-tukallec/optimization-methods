import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) {
        /*int k = 0;
        for (int n = 10; n <= 1000; n *= 10) {
            double[][] matrix = new double[n][n];
            for (int i = 0; i < n; i++) {
                int diag = 0;
                for (int j = 0; j < n; j++) {
                    if (i == j) {
                        continue;
                    }
                    matrix[i][j] = -random.nextInt(5);
                    diag += matrix[i][j];
                }
                matrix[i][i] = -diag + (i == 0 ? Math.pow(10, -k) : 0);
            }
            new MatrixGenerator("test", matrix);
            ProfileMatrix matrixProfile = new ProfileMatrix("test");
            matrixProfile.decompositionUL();
            Gauss gauss = new Gauss();
            double[] solution = gauss.ForwardGaussBasedOnLU(matrixProfile);
//            System.out.println(Arrays.stream(solution).mapToObj(l -> String.format("%.1f", l)).collect(Collectors.joining(" ")));
            System.out.println(n + " & " + k + " & " + mod(solution, matrixProfile.getB()));
        }*/
        ProfileMatrix matrixProfile = new ProfileMatrix("test");
        matrixProfile.decompositionUL();
        Gauss gauss = new Gauss();
        double[] solution = gauss.ForwardGaussBasedOnLU(matrixProfile);
        System.out.println(Arrays.stream(solution).mapToObj(l -> String.format("%.1f", l)).collect(Collectors.joining(" ")));
    }

    private static String mod(final double[] solution, final double[] expected) {
        double sum = 0;
        for (int i = 0; i < solution.length; i++) {
            sum += (solution[i] - expected[i]) * (solution[i] - expected[i]);
        }
        double norm = Math.sqrt(sum);
        double sumX = 0;
        for (final double v : expected) {
            sumX += v * v;
        }
        double normX = Math.sqrt(sumX);
        return String.format("%.10f", norm) + " & " + String.format("%.10f", norm / normX) + " \\\\";
    }
}
