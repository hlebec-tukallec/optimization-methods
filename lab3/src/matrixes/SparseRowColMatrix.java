package matrixes;

import java.io.File;
import java.io.IOException;

public class SparseRowColMatrix extends CommonMatrix {
    private double[] al;
    private double[] au;
    private double[] di;
    private int[] ia;
    private int[] ja;

    public double[] b;

    public int getN() {
        return di.length;
    }

    public SparseRowColMatrix(String dirName) {
        try {
            dirName = dirName + File.separator;
            ia = scanArrayInt(dirName, "ia");
            ja = scanArrayInt(dirName, "ja");
            di = scanArrayDouble(dirName, "di");
            al = scanArrayDouble(dirName, "al");
            au = scanArrayDouble(dirName, "au");
            b = scanArrayDouble(dirName, "b");
        } catch (IOException e) {
            System.err.println("IO Exception while reading input data: " + e.getMessage());
        }
    }

    public double getIJ(int i, int j) {
        if (i == j) {
            return di[i];
        }
        boolean low = false;
        if (i > j) {
            low = true;
        } else {
            int swap = i;
            i = j;
            j = swap;
        }
        final int realInJA = ia[i + 1] - ia[i];
        int offset = 0;
        for (; offset < realInJA; offset++) {
            if (ja[ia[i] + offset] == j) {
                break;
            } else if (ja[ia[i] + offset] > j) {
                return 0;
            }
        }
        if (offset == realInJA) {
            return 0;
        }
        return low ? al[ia[i] + offset] : au[ia[i] + offset];
    }
}
