package matrixes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class MatrixGenerator {
    private final String dirName;

    private final int n;
    private final double[][] matrix;
    private final int[] ia; //информация о профиле  от первого не нулевого до диагонального не включая диагональный
    private final double[] di; //диагональ, размер n
    private final double[] al; //элементы нижнего треугольника (L) (по строкам)
    private final double[] au; //элементы нижнего треугольника (U) (по столбцам)
    private final double[] b; //вектор правой части

    private final Random random = new Random();
    private final int diff = 11;

    // генерирует матрицу и записывает ее в профильном формате в директорию dir
    public MatrixGenerator(String dir) {
        dirName = dir + File.separator;

        n = generateN();
        matrix = generateMatrix();
        di = countDI();
        ia = countProfile();
        b = generateB();

        al = new double[ia[n] - 1];
        au = new double[ia[n] - 1];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            int cnt = ia[i + 1] - ia[i];
            for (int j = i - cnt; j < i; j++) {
                al[pos] = matrix[i][j];
                au[pos] = matrix[j][i];
                pos++;
            }
        }

        write();
    }

    // генерирует матрицу, число обусловленности которых регулируется за счёт изменения диагонального преобладания
    // так, чтобы решением являлся вектор [1, 2 ... n]
    public MatrixGenerator(String dir, int n, int k) {
        dirName = dir + File.separator;

        this.n = n;
        matrix = generateDiagonallyDominantMatrix();
        di = new double[n];
        for (int i = 0; i < n; i++) {
            int diag = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                diag += matrix[i][j];
            }
            di[i] = -diag + (i == 0 ? Math.pow(10, -k) : 0);
        }
        ia = countProfile();
        b = generateBFromOneToN();

        al = new double[ia[n] - 1];
        au = new double[ia[n] - 1];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            int cnt = ia[i + 1] - ia[i];
            for (int j = i - cnt; j < i; j++) {
                al[pos] = matrix[i][j];
                au[pos] = matrix[j][i];
                pos++;
            }
        }

        write();
    }

    // генерирует матрицу Гильберта так, чтобы решением являлся вектор [1, 2 ... n]
    public MatrixGenerator(String dir, int n) {
        dirName = dir + File.separator;

        this.n = n;
        matrix = generateGilbertMatrix();
        di = countDI();
        ia = countProfile();
        b = generateBFromOneToN();

        al = new double[ia[n] - 1];
        au = new double[ia[n] - 1];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            int cnt = ia[i + 1] - ia[i];
            for (int j = i - cnt; j < i; j++) {
                al[pos] = matrix[i][j];
                au[pos] = matrix[j][i];
                pos++;
            }
        }

        write();
    }

    // записывает данную плотную матрицу в профильном формате в директорию dir,
    public MatrixGenerator(String dir, double[][] m) {
        dirName = dir + File.separator;

        n = m.length;
        matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            System.arraycopy(m[i], 0, matrix[i], 0, n);
        }
        di = countDI();
        ia = countProfile();
        b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = m[i][n];
        }

        al = new double[ia[n] - 1];
        au = new double[ia[n] - 1];
        int pos = 0;
        for (int i = 0; i < n; i++) {
            int cnt = ia[i + 1] - ia[i];
            for (int j = i - cnt; j < i; j++) {
                al[pos] = matrix[i][j];
                au[pos] = matrix[j][i];
                pos++;
            }
        }

        write();
    }


    private void write() {
        Path dir = Path.of(dirName);
        if (!Files.exists(dir)) {
            try {
                Files.createDirectories(dir);
            } catch (IOException e) {
                System.err.println("Cannot create path. Access problems. " + e.getMessage());
            }
        }

        writeArrayIA();
        writeDoubleArray(di, dirName + "di");
        writeDoubleArray(al, dirName + "al");
        writeDoubleArray(au, dirName + "au");
        writeDoubleArray(b, dirName + "b");
    }




    private double[] generateBFromOneToN() {
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            for (int j = 1; j <= n; j++) {
                double cur;
                if (i == j - 1) {
                    cur = di[i];
                } else {
                    cur = matrix[i][j - 1];
                }
                b[i] += cur * j;
            }
        }
        return b;
    }

    private double[] generateB() {
        double[] b = new double[n];
        for (int i = 0; i < n; i++) {
            b[i] = random.nextDouble() * random.nextInt(diff);
        }
        return b;
    }

    private double[] countDI() {
        double[] di = new double[n];
        for (int i = 0; i < n; i++) {
            di[i] = matrix[i][i];
        }
        return di;
    }

    private int generateN() {
        int min = 10;
        int max = 20;
        int diff = max - min;
        int n = random.nextInt(diff + 1);
        n += min;
        return n;
    }

    private double[][] generateMatrix() {
        double[][] matrix = new double[n][n];
        matrix[0][0] = di[0] = random.nextDouble() * random.nextInt(diff);
        for (int i = 1; i < n; i++) {
            int posOfFirstNotZero = random.nextInt(i);
            generateFirstNotZero(posOfFirstNotZero, i);
            generateFirstNotZero(i, posOfFirstNotZero);

            for (int j = posOfFirstNotZero + 1; j <= i; j++) {
                generateProfile(i, j);
                generateProfile(j, i);
            }
        }
        return matrix;
    }

    private double[][] generateDiagonallyDominantMatrix() {
        double[][] matrix = new double[n][n];
        for (int i = 1; i < n; i++) {
            int posOfFirstNotZero = random.nextInt(i);

            matrix[i][posOfFirstNotZero] = 0;
            matrix[posOfFirstNotZero][i] = 0;
            while (matrix[i][posOfFirstNotZero] == 0 || matrix[posOfFirstNotZero][i] == 0) {
                matrix[i][posOfFirstNotZero] = -random.nextInt(5);
                matrix[posOfFirstNotZero][i] = -random.nextInt(5);
            }

            for (int j = posOfFirstNotZero + 1; j <= i; j++) {
                matrix[i][j] = -random.nextInt(5);
                matrix[j][i] = -random.nextInt(5);
            }
        }
        return matrix;
    }

    private double[][] generateGilbertMatrix() {
        double[][] matrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                double del = (i + j + 1);
                matrix[i][j] = matrix[j][i] = 1 / del;
            }
        }
        return matrix;
    }

    private void generateFirstNotZero(final int i, final int posOfFirstNotZero) {
        matrix[posOfFirstNotZero][i] = randomNotZeroDouble() * (random.nextInt(diff) + 1);
        if (random.nextBoolean()) {
            matrix[posOfFirstNotZero][i] = -matrix[posOfFirstNotZero][i];
        }
    }

    private double randomNotZeroDouble() {
        return random.nextDouble() + 0.000001;
    }

    private void generateProfile(final int i, final int j) {
        int next = random.nextInt(3);
        double d = randomNotZeroDouble() * (random.nextInt(diff) + 1);
        switch (next) {
            case 1 -> matrix[i][j] = -d;
            case 2 -> matrix[i][j] = d;
            default -> matrix[i][j] = 0;
        }
    }

    private int[] countProfile() {
        int[] ia = new int[n + 1];
        ia[0] = ia[1] = 1;
        for (int i = 1; i < n; i++) {
            int tmp = 0;
            for (int j = 0; j < i; j++) {
                if (tmp == 0 && matrix[i][j] == 0) {
                    continue;
                }
                tmp++;
            }
            ia[i + 1] = ia[i] + tmp;
        }
        return ia;
    }

    private void writeArrayIA() {

        try (BufferedWriter out = new BufferedWriter(new FileWriter(dirName + "ia.txt"))) {
            for (int j : ia) {
                out.write(j + " ");
            }
        } catch (IOException e) {
            System.err.println("Couldn't write ia.txt. " + e.getMessage());
        }
    }

    private void writeDoubleArray(double[] array, String path) {
        try (BufferedWriter out = new BufferedWriter(new FileWriter(path + ".txt"))) {
            for (double a : array) {
                out.write(a + " ");
            }
        } catch (IOException e) {
            System.err.println("Couldn't write " + path + ". " + e.getMessage());
        }
    }

    private void show() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
