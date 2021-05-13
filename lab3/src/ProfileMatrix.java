import java.util.Scanner;

public class ProfileMatrix {
    int[] ia;
    double[] di;
    double[] al;
    double[] au;

    public ProfileMatrix(String dirName) {
        scanArrayIA(dirName);
        di = scanArrayDouble(dirName, "di");
        al = scanArrayDouble(dirName, "al");
        au = scanArrayDouble(dirName, "au");
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

        if (j < i - x + 1) { // beginning zero
            return 0d;
        }
        int pos = y + j - (i - x + 1);
        return up ? au[pos] : al[pos];
    }

    private void scanArrayIA(String dirName) {
        Scanner scanner = new Scanner(dirName + "ia");
        int n = scanN(scanner);
        int it = 0;
        ia = new int[n];
        while (scanner.hasNext()) {
            ia[it++] = scanner.nextInt();
        }
    }

    private void scanArrayDouble(String file, double[] arr) {
        Scanner scanner = new Scanner(dirName + file);
        int it = 0;
        int n = scanN(scanner);
        int it = 0;
        final double[] array = new double[n];
        while (scanner.hasNext()) {
            array[it++] = scanner.nextDouble();
        }
        return array;
    }

    private int scanN(Scanner scanner) throws NumberFormatException {
        return scanner.nextInt();
    }
}
