public class Main {
    public static void main(String[] args) {
        Method dich = new Dichotomy();
        System.out.println("Dichotomy: " + dich.print());

        Method gold = new Gold();
        System.out.println("Golden Section: " + gold.print());

        Method fib = new Fibonacci();
        System.out.println("Fib: " + fib.print());
    }

    //6. f(x) = −5x^5 + 4x^4 − 12x^3 + 11x^2 − 2x + 1 → min на интервале [-0.5; 0.5]
    //wolfram says: 0.10986

}
