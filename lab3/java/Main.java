import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int tests;
        Scanner scan = new Scanner(System.in);
        tests = scan.nextInt();
        for (int i = 0; i < tests; i++) {
            String name = "test" + (i + 1);
            MatrixGenerator matrixGenerator = new MatrixGenerator(name);
            matrixGenerator.printMatrix();
        }
    }
}
