public class MatrixOperation {
    public static double[] multiply(double[] a, double[][] b) {
        int n = a.length;
        double[] c = new double[n];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j) {
                c[i] += a[j] * b[j][i];
            }
        }
        return c;
    }

    public static double[] multiply(double[][] a, double[] b) {
        return multiply(b, a);
    }

    public static double multiply(double[] a, double[] b) {
        int n = a.length;
        double c = 0;
        for (int i = 0; i < n; ++i) {
            c += a[i] * b[i];
        }
        return c;
    }


    public static double[] add(double[] a, double[] b) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; ++i) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    public static double[] subtract(double[] a, double[] b) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; ++i) {
            c[i] = a[i] - b[i];
        }
        return c;
    }

    public static double[] multiply(double[] a, double b) {
        int l = a.length;
        double[] c = new double[l];
        for (int i = 0; i < l; ++i) {
            c[i] = a[i] * b;
        }
        return c;
    }
}
