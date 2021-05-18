import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class ProfileMatrix {
    private int[] ia;
    private double[] di;
    private double[] al;
    private double[] au;
    private double[] b;

    public ProfileMatrix(String dirName) {
        try {
            dirName = dirName + File.separator;
            scanArrayIA(dirName);
            di = scanArrayDouble(dirName, "di");
            al = scanArrayDouble(dirName, "al");
            au = scanArrayDouble(dirName, "au");
            b = scanArrayDouble(dirName, "b");
        } catch (IOException e) {
            System.err.println("IO Exception while reading input data: " + e.getMessage());
        }
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

    public double getB(final int i) {
        return b[i];
    }

    public double[] getB() {
        return b;
    }

    public int getN() {
        return di.length;
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
                    double add;
                    add = get(i, k);
                    sum += add * get(k, j);
                }
                set(i, j, get(i, j) - sum);
            }

            for (int j = i + 1; j < n; j++) {
                double sum = 0;
                for (int k = 0; k < i; k++) {
                    sum += get(j, k) * get(k, i);
                }
                double cur = get(i, i);
                set(j, i, (get(j, i) - sum) / cur);
            }
        }

    }

    private void scanArrayIA(String dirName) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Path.of(dirName + "ia.txt"));
        ia = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    private double[] scanArrayDouble(String dirName, String fileName) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Path.of(dirName + fileName + ".txt"));
        return Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
    }

    @Override
    public String
    toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < di.length; i++) {
            for (int j = 0; j < di.length; j++) {
                str.append(String.format("%.5f",get(i, j))).append(" ");
            }
            str.append('\n');
        }
        return str.toString();
    }
}
