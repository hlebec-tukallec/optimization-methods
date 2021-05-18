import java.util.Arrays;

public class Gauss {
    // Решение хранится в векторе b (правая часть)
    public double[] ForwardGaussBasedOnLU(ProfileMatrix matrix) {
        int n = matrix.getN();
        for (int i = 1; i < n; i++) {
            double cur = 0;
            for (int j = 0; j < i; j++) {
                cur += matrix.getFromLU(i, j) * matrix.getFromB(j);
            }
            matrix.setInB(i, matrix.getFromB(i) - cur);
        }

        for (int i = n - 1; i >= 0; i--) {
            double cur = 0;
            for (int j = n - 1; j > i; j--) {
                cur += matrix.getFromLU(i, j) * matrix.getFromB(j);
            }
            matrix.setInB(i, (matrix.getFromB(i) - cur) / matrix.getFromLU(i, i));
        }
        return matrix.getB();
    }

    public double[] GaussWithPivotElement(double[][] a) {
        int m = a.length + 1;
        int n = m - 1;

        int[] realRows = new int[n];
        for (int i = 0; i < n; i++) {
            realRows[i] = i;
        }

        for (int row = 0; row < n; row++) {
            int sel = row;
            for (int i = row + 1; i < n; i++) {
                if (Math.abs(a[realRows[i]][row]) > Math.abs(a[realRows[sel]][row])) {
                    sel = i;
                }
            }
            int tmp = realRows[sel];
            realRows[sel] = realRows[row];
            realRows[row] = tmp;

            for (int i = row + 1; i < n; ++i) {
                double c = a[realRows[i]][row] / a[realRows[row]][row];
                for (int j = row; j < m; ++j) {
                    a[realRows[i]][j] -= a[realRows[row]][j] * c;
                }
            }
        }

        /*for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(a[realRows[i]][j] + " ");
            }
            System.out.println();
        }*/
        double[] ans = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = 0; j < m; ++j)
                sum += ans[j] * a[i][j];
            if (Math.abs(sum - a[i][m]) > 0.000001)
                return ans;
        }

        for (int i = 0; i < m; ++i)
            if (where[i] == -1)
                return ans;
        return ans;
    }
}

