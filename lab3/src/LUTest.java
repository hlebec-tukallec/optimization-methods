import java.io.FileNotFoundException;

public class LUTest {
    private static final String name = "test/";

    public static void main(String[] args) throws FileNotFoundException {
//        MatrixGenerator generator = new MatrixGenerator("test/");
//        generator.printMatrix();
        ProfileMatrix matrix = new ProfileMatrix("test/");
        System.out.println(matrix.toString());
        matrix.decompose();
        System.out.println(matrix.toString());
    }

}
