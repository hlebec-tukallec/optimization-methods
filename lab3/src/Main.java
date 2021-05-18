import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Тестирование матриц с диагональным преобладанием
        for (int k = 0; k < 10; k++) {
            for (int n = 10; n <= 1000; n *= 10) {
                new MatrixGenerator("test", n, k);
                ProfileMatrix matrixProfile = new ProfileMatrix("test");
                matrixProfile.decompositionUL();
                double[] solution = new Gauss().ForwardGaussBasedOnLU(matrixProfile);
                System.out.println(n + " & " + k + " & " + mod(solution));
            }
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

    private static String mod(final double[] solution) {
        double sum = 0;
        for (int i = 0; i < solution.length; i++) {
            sum += (solution[i] - (i + 1)) * (solution[i] - (i + 1));
        }
        double norm = Math.sqrt(sum);
        double sumX = 0;
        for (int v = 1; v <= solution.length; v++) {
            sumX += v * v;
        }
        double normX = Math.sqrt(sumX);
        return String.format("%.20f", norm) + " & " + String.format("%.20f", norm / normX) + " \\\\";
    }
}
