import java.util.Random;

public class Main {
    public static void main(String[] args) {
        // Тестирование матриц с диагональным преобладанием
        for (int k = 0; k < 10; k++) {
            for (int n = 10; n <= 1000; n *= 10) {
                new MatrixGenerator("test", n, k);
                ProfileMatrix matrixProfile = new ProfileMatrix("test");
                matrixProfile.decompositionUL();
                double[] solution = new Gauss().ForwardGaussBasedOnLU(matrixProfile);
                System.out.println(n + " & " + k + " & " + norm(solution) + " \\\\");
            }
        }

        // Тестирование матриц Гильберта
        for (int n = 2; n < 10; n++) {
            new MatrixGenerator("test", n);
            ProfileMatrix matrixProfile = new ProfileMatrix("test");
            matrixProfile.decompositionUL();
            double[] solution = new Gauss().ForwardGaussBasedOnLU(matrixProfile);

            System.out.println(n + " & " + norm(solution));
        }
        for (int n = 10; n < 20; n += 2) {
            new MatrixGenerator("test", n);
            ProfileMatrix matrixProfile = new ProfileMatrix("test");
            matrixProfile.decompositionUL();
            double[] solution = new Gauss().ForwardGaussBasedOnLU(matrixProfile);

            System.out.println(n + " & " + norm(solution));
        }
        for (int n = 20; n <= 100; n += 10) {
            new MatrixGenerator("test", n);
            ProfileMatrix matrixProfile = new ProfileMatrix("test");
            matrixProfile.decompositionUL();
            double[] solution = new Gauss().ForwardGaussBasedOnLU(matrixProfile);

            System.out.println(n + " & " + norm(solution));
        }

        // Сравнение метода Гаусса по точности получаемого решения и по количеству действий с реализованным прямым методом LU-разложения
        Random random = new Random();
        for (int n = 970; n <= 10000; n += 500) {
            double[][] m = new double[n][n + 1];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    m[i][j] = (random.nextDouble() + 0.0001) * (random.nextInt(100) + 1);
                }
            }
            for (int i = 0; i < n; i++) {
                for (int j = 1; j <= n; j++) {
                    m[i][n] += m[i][j - 1] * j;
                }
            }

            new MatrixGenerator("test", m);
            ProfileMatrix matrixProfile = new ProfileMatrix("test");
            matrixProfile.decompositionUL();
            double[] LUSolution = new Gauss().ForwardGaussBasedOnLU(matrixProfile);

            double[] gaussPivotSolution = new Gauss().GaussWithPivotElement(m);

            System.out.println(n + " & " + norm(LUSolution) + " & " + norm(gaussPivotSolution) + " \\\\");
        }
    }

    private static String norm(final double[] solution) {
        double sumSub = 0;
        for (int i = 0; i < solution.length; i++) {
            sumSub += (solution[i] - (i + 1)) * (solution[i] - (i + 1));
        }
        double norm = Math.sqrt(sumSub);
        double sumX = 0;
        for (int i = 1; i <= solution.length; i++) {
            sumX += i * i;
        }
        double normX = Math.sqrt(sumX);
        return String.format("%.20f", norm / normX) + " ";
    }
}
