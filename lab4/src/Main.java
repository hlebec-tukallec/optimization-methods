import newton.Newton;
import utils.Point;
import utils.Source;

public class Main {
    private static int mod = 1;

    public static void main(String[] args) {
        Source source = new Source();
        source.changeMod(mod);

        Point res = new Newton().minimum(source.getFunction(), source.getPoint(), 0.000000001);
        System.out.println(res);
    }
}