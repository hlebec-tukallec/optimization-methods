import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class MatrixGenerator {
    int n;
    String fileName;
    Random random = new Random();
    int[][] matrix;
    int[] di; //диагональ, размер н, double?
    int[] al; //элементы нижнего треугольника (L) (по строкам) double?
    int[] au; //элементы нижнего треугольника (U) (по столбцам) double?
    int[] ia; //информация о профиле  от первого не нулевого до диагонального не включая диагональный

    public MatrixGenerator(String name) {
        this.fileName = name;
    }

    public void generateN() {
        int min = 10;
        // int max = 1000;
        int max = 20;
        int diff = max - min;
        int n = random.nextInt(diff + 1);
        n += min;
        this.n = n;
    }

    private void generateMatrix() {
        this.matrix = new int[n][n];
        this.di = new int[n];
        int diff = 1000;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                boolean sign = random.nextBoolean();
                if (!sign) {
                    this.matrix[i][j] = random.nextInt(diff + 1) * -1;
                } else {
                    this.matrix[i][j] = random.nextInt(diff + 1);
                }
                if (i == j) {
                    this.di[i] = matrix[i][j];
                }
            }
        }
    }

    //для тестов руками
    private void readMatrix() {
        this.matrix = new int[][]{{1, 3, 4, 0},
                {0, 5, 7, 6},
                {0, 1, 4, 3},
                {0, 0, 4, 6}};
        this.n = matrix.length;
    }

    private void countLU() {
        this.al = new int[ia[n] - 1];
        this.au = new int[ia[n] - 1];

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
        this.ia = new int[n + 1];
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
        File tmp = new File(fileName + "/ia");
        if (!tmp.getParentFile().mkdirs()) {
            System.err.println("Cannot create path. Access problems");
        }
        try (BufferedWriter out = new BufferedWriter(new FileWriter(tmp, StandardCharsets.UTF_8))) {
            for (int j : ia) {
                out.write(j + " ");
            }
        } catch (IOException e) {
            System.err.println("check ai");
        }

        tmp = new File(fileName + "/di");
        try (BufferedWriter out = new BufferedWriter(new FileWriter(tmp))) {
            for (int j : di) {
                out.write(j + " ");
            }
        } catch (IOException e) {
            System.err.println("check di");
        }

        tmp = new File(fileName + "/al");
        try (BufferedWriter out = new BufferedWriter(new FileWriter(tmp))) {
            for (int j : al) {
                out.write(j + " ");
            }
        } catch (IOException e) {
            System.err.println("check al");
        }

        tmp = new File(fileName + "/au");
        try (BufferedWriter out = new BufferedWriter(new FileWriter(tmp))) {
            for (int j : au) {
                out.write(j + " ");
            }
        } catch (IOException e) {
            System.err.println("check au");
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
