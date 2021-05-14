import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class LUTest {
    private static final String name = "test/";

    public static void main(String[] args) throws IOException {
        MatrixGenerator generator = new MatrixGenerator("test/");
        generator.printMatrix();
        ProfileMatrix matrix = new ProfileMatrix("test/");
        System.out.println(matrix.toString());
//        matrix.decompositionUL();
//        Gauss gauss = new Gauss();
//        System.out.println(Arrays.toString(gauss.ForwardGaussBasedOnLU(matrix)));
    }

}
