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
        int n = a.length;
        int m = n - 1;

        int[] where = new int[m];
        Arrays.fill(where, -1);
        for (int col = 0, row = 0; col < m && row < n; ++col) {
            int sel = row;
            for (int i = row; i < n; ++i)
                if (Math.abs(a[i][col]) > Math.abs(a[sel][col]))
                    sel = i;
            if (Math.abs(a[sel][col]) < 0.00001)
                continue;
            for (int i = col; i <= m; ++i) {
                double tmp = a[sel][i];
                a[sel][i] = a[row][i];
                a[row][i] = tmp;
            }
            where[col] = row;

            for (int i = 0; i < n; ++i)
                if (i != row) {
                    double c = a[i][col] / a[row][col];
                    for (int j = col; j <= m; ++j)
                        a[i][j] -= a[row][j] * c;
                }
            ++row;
        }

        double[] ans = new double[m];
        for (int i = 0; i < m; ++i)
            if (where[i] != -1)
                ans[i] = a[where[i]][m] / a[where[i]][i];
        for (int i = 0; i < n; ++i) {
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

