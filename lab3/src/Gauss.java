import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Gauss {
    private static double[] di;
    private static int[] ia;
    private static double[] al;
    private static double[] au;
    private static double[] b;
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(new File(args[0]));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public double[] SolutionGauss(double[][] matrix) {
        int n = matrix.length;
        double[] y = new double[n];
        for (int i = 0; i < n; i++) {
            double cur = 0;
            for (int j = 0; j < i; j++) {
                cur += matrix[i][j] * y[j];
            }
            y[i] = b[i] - cur;
        }

        for (int i = n; i >= 0; i--) {
            double cur = 0;
            for (int j = n; j > i; j--) {
                cur += matrix[i][j] * y[j];
            }
            y[i] = (b[i] - cur) / matrix[i][i];
        }

        return y;
    }

    // 0 - index
    private double get(int i, int j) {
        if (i == j) {
            return di[i];
        }
        int x = ia[i + 1] - ia[i]; // profile of i-th string
        int y = ia[i] - 1; // position of first element of i-th string in al/au
        if (((j < i) && (j < i - x + 1)) || // beginning zero
            ((j > i) && (j > i + x))) { // ending zeros
                return 0d;
        }
        int pos = y + j - (i - x + 1);
        return j < i ? al[pos] : au[pos];
    }
}

