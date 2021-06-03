import newton.Newton;
import newton.NewtonDirectionDescent;
import newton.NewtonOptimisation;
import quasinewton.BroadenFletcherChen;
import quasinewton.Powell;
import utils.Method;
import utils.Point;
import utils.Source;

public class Main {
/*
    0: 1.2.1
    1: 1.2.2 / 2.1
    2: 2.2
    3: 2.3
    4: 2.4
    5: 3x^2+xy+2y^2-x-4y (1.1.1)
    6: -y*sqrt(x)+2*y^2+x-14y (1.1.2)
*/
    private static int mod = 0;
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

        for (int i = 0; i < 5; i++) {
            methodMod = i;
            Method method;
            switch (methodMod) {
                case (0): {
                    method = new Newton();
                    System.out.println("Newton");
                    break;
                }
                case (1): {
                    method = new NewtonDirectionDescent();
                    System.out.println("NewtonDirectionDescent");
                    break;
                }
                case (2): {
                    method = new NewtonOptimisation();
                    System.out.println("NewtonOptimisation");
                    break;
                }
                case (3): {
                    method = new BroadenFletcherChen();
                    System.out.println("NewtonFletcherChen");
                    break;
                }
                case (4): {
                    method = new Powell();
                    System.out.println("Powell");
                    break;
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
}