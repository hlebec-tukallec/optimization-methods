import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final Random random = new Random();

    public static void main(String[] args) throws IOException {
//        int tests;
//        Scanner scan = new Scanner(System.in);
//        tests = scan.nextInt();
//        for (int i = 0; i < tests; i++) {
//            String name = "test" + (i + 1);
//            MatrixGenerator matrixGenerator = new MatrixGenerator(name);
//            matrixGenerator.printMatrix();
//        }

        int k = 5;
        for (int n = 10; n <= 1000; n *= 10) {
            double[][] matrix = new double[n][n];
            for (int i = 0; i < n; i++) {
                int diag = 0;
                for (int j = 0; i != j && j < n; j++) {
                    matrix[i][j] = -random.nextInt(5);
                    diag += matrix[i][j];
                }
                matrix[i][i] = -diag + (i == 0 ? Math.pow(10, -k) : 0);
            }
            MatrixGenerator generator = new MatrixGenerator("test");
            generator.readMatrix(matrix);
            generator.printMatrix();
            ProfileMatrix matrixProfile = new ProfileMatrix("test/");
            matrixProfile.decompositionUL();
            Gauss gauss = new Gauss();
            double[] solution = gauss.ForwardGaussBasedOnLU(matrixProfile);
            System.out.println(n + " & " + k + " & " + mod(solution));
        }
    }
}
