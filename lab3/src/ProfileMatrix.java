import java.util.Scanner;

public class ProfileMatrix {
    private static int[] ia;
    private static double[] di;
    private static double[] al;
    private static double[] au;
    private final String dirName;

    public ProfileMatrix(String dirName) {
        this.dirName = dirName;
        MatrixGenerator matrixGenerator = new MatrixGenerator(dirName);
        matrixGenerator.printMatrix();
        scanData();
    }


    private void scanData() {
        scanIA();
        scanArrayDouble("di", di);
        scanArrayDouble("al", al);
        scanArrayDouble("du", au);
    }

    private void scanIA() {
        Scanner scanner = new Scanner(dirName + "/ia");
        int it = 0;
        int n = scanN(scanner);
        ia = new int[n];
        while (scanner.hasNext()) {
            try {
                ia[it] = scanner.nextInt();
            } catch (NumberFormatException e) {
                System.out.println("ia consists integer");
            }
            it++;
        }
    }

    private void scanArrayDouble(String file, double[] arr) {
        Scanner scanner = new Scanner(dirName + file);
        int it = 0;
        int n = scanN(scanner);
        arr = new double[n];
        while (scanner.hasNext()) {
            try {
                arr[it] = scanner.nextDouble();
            } catch (NumberFormatException e) {
                System.out.println(file + " consists integer");
            }
            it++;
        }
    }

    private int scanN(Scanner scanner) throws NumberFormatException {
        if (scanner.hasNextInt()) {
            return scanner.nextInt();
        }
        throw new NumberFormatException("The first argument - int n - expected");
    }
}
