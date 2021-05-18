import java.util.Arrays;

public class LUTest {

    public static void main(String[] args) {
        new MatrixGenerator("test1/");
        ProfileMatrix matrix = new ProfileMatrix("test1/");
        System.out.println(matrix.toString());
        matrix.decompositionUL();
        System.out.println(matrix.toString());

        Gauss gauss = new Gauss();
        System.out.println(Arrays.toString(gauss.ForwardGaussBasedOnLU(matrix)));
    }

}
