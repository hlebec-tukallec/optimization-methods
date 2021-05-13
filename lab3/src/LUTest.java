import java.util.Scanner;

public class LUTest {
    private static int[] ia;
    private static double[] di;
    private static double[] al;
    private static double[] au;
    private static final String name = "test/";

    public static void main(String[] args) {
        DecompositionLUprofile decomposition = new DecompositionLUprofile(ia, di, al, au, di.length);
        decomposition.decompose();
    }

}
