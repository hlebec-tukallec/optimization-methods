import java.util.Arrays;

public class DecompositionLUprofile {
    private double[][] l;
    private double[][] u;
    private final int[] ia;
    private final double[] di;
    private final double[] al;
    private final double[] au;
    private final int n;


    public DecompositionLUprofile(int[] ia, double[] di, double[] al, double[] au, int n) {
        this.ia = ia;
        this.di = di;
        this.al = al;
        this.au = au;
        this.n = n;
        this.l = new double[n][n];
        this.u = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                u[i][j] = get(i, j);
            }
        }
    }

    private double get(int i, int j) {
        if (i == j) {
            return di[i];
        }
        boolean up = false;
        if (j > i) {
            up = true;
            int swap = j;
            j = i;
            i = swap;
        }

        int x = ia[i + 1] - ia[i]; // profile of i-th string
        int y = ia[i] - 1; // position of first element of i-th string in al/au

        if (j < i - x + 1) { // beginning zero
            return 0d;
        }
        int pos = y + j - (i - x + 1);
        return up ? au[pos] : al[pos];
    }

    public void decompose() {
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
