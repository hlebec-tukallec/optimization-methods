import newton.Newton;
import newton.NewtonDirectionDescent;
import newton.NewtonOptimisation;
import quasinewton.BroadenFletcherChen;
import quasinewton.Powell;
import utils.Method;
import utils.Point;
import utils.Source;

public class Main {
    private static int mod = 5;

    public static void main(String[] args) {
        Source source = new Source();
        source.changeMod(mod);

        /*Method newton = new Newton();
        Point res = newton.minimum(source.getFunction(), source.getPoint(), 0.000001);
        System.out.println("ans" + res);
        System.out.println(newton.getCountOfIterations());
        System.out.println();*/

        Method newtonDirect = new NewtonDirectionDescent();
        Point res = newtonDirect.minimum(source.getFunction(), source.getPoint(), 0.000001);
        System.out.println("ans" + res);
        System.out.println(newtonDirect.getCountOfIterations());
        System.out.println();

      /*  Method newtonOptimisation = new NewtonOptimisation();
        res = newtonOptimisation.minimum(source.getFunction(), source.getPoint(), 0.000001);
        System.out.println("ans" + res);
        System.out.println(newtonOptimisation.getCountOfIterations());
*/
        /*res = new NewtonDirectionDescent().minimum(source.getFunction(), source.getPoint(), 0.0001);
        System.out.println(res); // ok
        res = new NewtonOptimisation().minimum(source.getFunction(), source.getPoint(), 0.0001);
        System.out.println(res); // ok
        res = new BroadenFletcherChen().minimum(source.getFunction(), source.getPoint(), 0.0001);
        System.out.println(res); //ok
        res = new Powell().minimum(source.getFunction(), source.getPoint(), 0.0001);
        System.out.println(res); //ok*/
    }
}