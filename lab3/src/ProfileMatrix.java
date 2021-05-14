import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class ProfileMatrix {
    int[] ia;
    double[] di;
    double[] al;
    double[] au;
    double[] b;

    public ProfileMatrix(String dirName) throws FileNotFoundException {
        scanArrayIA(dirName);
        di = scanArrayDouble(dirName, "di");
        al = scanArrayDouble(dirName, "al");
        au = scanArrayDouble(dirName, "au");
        b = scanArrayDouble(dirName, "b");
    }

    public double get(int i, int j) {
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

        int realPos = i - x;
        if (j < realPos) { // beginning zero
            return 0d;
        }
        int pos = y + j - realPos;
        return up ? au[pos] : al[pos];
    }

    public void set(int i, int j, double value) {
        if (i == j) {
            di[i] = value;
            return;
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

        int realPos = i - x;
        if (j < realPos) {
            return; // beginning zero
        }
        int pos = y + j - realPos;
        if (up) {
            au[pos] = value;
        } else {
            al[pos] = value;
        }
    }

    //todo разложение не всегда есть, это надо обработать
    public void decompositionUL() {
        int n = di.length;

        double u00 = get(0, 0);
        for (int j = 1; j < n; j++) {
            set(j, 0, get(j, 0) / u00);
        }
        for (int i = 1; i < n; i++) {
            for (int j = i; j < n; j++) {
                double sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += get(i, k) * get(k, j);
                }
                set(i, j, get(i, j) - sum);
            }

            for (int j = i + 1; j < n; j++) {
                double sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += get(j, k) * get(k, i);
                }
                set(j, i, (get(j, i) - sum) / get(i, i));
            }
        }

    }

    private void scanArrayIA(String dirName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(dirName + "ia.txt"));
            int n = scanN(scanner);
            int it = 0;
            ia = new int[n];
            while (scanner.hasNext()) {
                ia[it++] = scanner.nextInt();
            }
    }

    private double[] scanArrayDouble(String dirName, String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(dirName + fileName + ".txt"));
        int it = 0;
        int n = scanN(scanner);
        final double[] array = new double[n];
        while (scanner.hasNext()) {
            array[it++] = scanner.nextDouble();
        }
        return array;
    }

    private int scanN(Scanner scanner) {
        return scanner.nextInt();
    }

    @Override
    public String
    toString() {
        return "ProfileMatrix{" +
                "ia=" + Arrays.toString(ia) +
                ", di=" + Arrays.toString(di) +
                ", al=" + Arrays.toString(al) +
                ", au=" + Arrays.toString(au) +
                '}';
    }
}
