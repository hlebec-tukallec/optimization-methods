import newton.Newton;
import newton.NewtonDirectionDescent;
import newton.NewtonOptimisation;
import quasinewton.BroadenFletcherChen;
import quasinewton.Powell;
import utils.Point;
import utils.Source;

public class Main {
    private static int mod = 3;

    public static void main(String[] args) {
        Source source = new Source();
        source.changeMod(mod);

        Point res = new Newton().minimum(source.getFunction(), source.getPoint(), 0.001);
        System.out.println(res); //ne ok
        res = new NewtonDirectionDescent().minimum(source.getFunction(), source.getPoint(), 0.0001);
        System.out.println(res); // ok
        res = new NewtonOptimisation().minimum(source.getFunction(), source.getPoint(), 0.0001);
        System.out.println(res); // ok
        res = new BroadenFletcherChen().minimum(source.getFunction(), source.getPoint(), 0.0001);
        System.out.println(res); //ok
        res = new Powell().minimum(source.getFunction(), source.getPoint(), 0.0001);
        System.out.println(res); //ok
    }
}