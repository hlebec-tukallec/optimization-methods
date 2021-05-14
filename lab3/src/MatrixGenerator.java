import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;

public class MatrixGenerator {
    private int n;
    private final String dirName;
    private final Random random = new Random();
    private double[][] matrix;
    private int[] ia; //информация о профиле  от первого не нулевого до диагонального не включая диагональный
    private double[] di; //диагональ, размер n
    private double[] al; //элементы нижнего треугольника (L) (по строкам) double?
    private double[] au; //элементы нижнего треугольника (U) (по столбцам) double?
    private double[] b; //вектор правой части


    public MatrixGenerator(String name) {
        dirName = name + File.separator;
    }

    public void generateN() {
        int min = 10;
        int max = 20;
        int diff = max - min;
        int n = random.nextInt(diff + 1);
        n += min;
//        this.n = n;
        this.n = 4;
    }

    private void generateMatrix() {
        matrix = new double[n][n];
        b = new double[n];
        di = new double[n];
        int diff = 11;
        matrix[0][0] = di[0] = random.nextDouble() * random.nextInt(diff);
        for (int i = 1; i < n; i++) {
            int posOfFirstNotZero = random.nextInt(i);
            generateFirstNotZero(diff, posOfFirstNotZero, i);
            generateFirstNotZero(diff, i, posOfFirstNotZero);

            for (int j = posOfFirstNotZero + 1; j < i; j++) {
                generateProfile(diff, i, j);
                generateProfile(diff, j, i);
            }
            matrix[i][i] = di[i] = random.nextDouble() * random.nextInt(diff);
            b[i] = random.nextDouble() * random.nextInt(diff);
        }
    }

    private void generateFirstNotZero(final int diff, final int i, final int posOfFirstNotZero) {
        matrix[posOfFirstNotZero][i] = randomNotZeroDouble() * (random.nextInt(diff) + 1);
        if (random.nextBoolean()) {
            matrix[posOfFirstNotZero][i] = -matrix[posOfFirstNotZero][i];
        }
    }

    private double randomNotZeroDouble() {
        return random.nextDouble() + 0.000001;
    }

    private void generateProfile(final int diff, final int i, final int j) {
        int next = random.nextInt(3);
        double d = randomNotZeroDouble() * (random.nextInt(diff) + 1);
        switch (next) {
            case 1 -> matrix[i][j] = -d;
            case 2 -> matrix[i][j] = d;
            default -> matrix[i][j] = 0;
        }
    }

    //для тестов руками
    private void readMatrix() {
        matrix = new double[][]{{1, 3, 4, 0},
                {0, 5, 7, 6},
                {0, 1, 4, 3},
                {0, 0, 4, 6}};
        n = matrix.length;
    }

    private void countLU() {
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
    }

    private void countProfile() {
        ia = new int[n + 1];
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
    }

    private void write() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
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

    public void printMatrix() {
        generateN();
        generateMatrix();
        //readMatrix();
        countProfile();
        countLU();
        write();

        //вывод самой матрицы для тестов
//        for (int[] ints : matrix) {
//            for (int j = 0; j < matrix.length; j++) {
//                System.out.print(ints[j]);
//                System.out.print(" ");
//            }
//            System.out.println();
//        }
//        System.out.println();

    }
}
