import java.util.Arrays;

public class DecompositionLUprofile {


    public ProfileMatrix decompose(ProfileMatrix A) {
        int n = A.di.length;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                l[j][i] = u[j][i] / u[i][i];
            }
        }
        for (int k = 1; k < n; k++) {
            for (int i = k - 1; i < n; i++) {
                for (int j = i; j < n; j++) {
                    l[j][i] = u[j][i] / u[i][i];
                }
            }
            for (int i = k; i < n; i++) {
                for (int j = k - 1; j < n; j++) {
                    u[i][j] = u[i][j] - l[i][k - 1] * u[k - 1][j];
                }
            }
        }
        printMatrix(combine());
    }

    private double[][] combine() {
        double[][] A = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i <= j) {
                    A[i][j] = u[i][j];
                } else {
                    A[i][j] = l[i][j];
                }
            }
        }
        return A;
    }

    private void printMatrix(double[][] mx) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.println(mx[i][j]);
            }
        }
    }
}
