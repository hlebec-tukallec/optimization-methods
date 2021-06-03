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
/*
    0: Newton();
    1: NewtonDirectionDescent();
    2: NewtonOptimisation();
    3: BroadenFletcherChen();
    4: Powell();
 */
    private static int methodMod = 0;

    public static void main(String[] args) {
        Source source = new Source();
        source.changeMod(mod);

        Method method;
        switch (methodMod) {
            case (0): {
                method = new Newton();
            }
            case (1): {
                method = new NewtonDirectionDescent();
            }
            case (2): {
                method = new NewtonOptimisation();
            }
            case (3): {
                method = new BroadenFletcherChen();
            }
            case (4): {
                method = new Powell();
            }
            default: {
                method = new Newton();
            }

        }
        Point res = method.minimum(source.getFunction(), source.getPoint(), 0.000001);
        System.out.println("ans" + res);
        System.out.println(method.getCountOfIterations());
        System.out.println();
    }
}