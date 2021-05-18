import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       /* // Тестирование матриц с диагональным преобладанием
        for (int k = 0; k < 10; k++) {
            for (int n = 10; n <= 1000; n *= 10) {
                new MatrixGenerator("test", n, k);
                ProfileMatrix matrixProfile = new ProfileMatrix("test");
                matrixProfile.decompositionUL();
                double[] solution = new Gauss().ForwardGaussBasedOnLU(matrixProfile);

                double norm = norm(solution);

                double sumX = 0;
                for (int v = 1; v <= solution.length; v++) {
                    sumX += v * v;
                }
                double normX = Math.sqrt(sumX);

                System.out.println(n + " & " + k + " & + norm(solution) + " \\\\");
            }
        }*/

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
//        System.out.println(Arrays.stream(solution).mapToObj(l -> String.format("%.1f", l)).collect(Collectors.joining(" ")));
//        ProfileMatrix matrixProfile = new ProfileMatrix("test");
//        System.out.println(matrixProfile);
//        matrixProfile.decompositionUL();
//        System.out.println(matrixProfile);
//        Gauss gauss = new Gauss();
//        double[] solution = gauss.ForwardGaussBasedOnLU(matrixProfile);
//        System.out.println(Arrays.stream(solution).mapToObj(l -> String.format("%.1f", l)).collect(Collectors.joining(" ")));
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
        return String.format("%.20f", norm) + " & " + String.format("%.20f", norm / normX) + " \\\\";
    }
}
