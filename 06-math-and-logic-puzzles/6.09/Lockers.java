public class Lockers {

    private static final int N = 100;

    public static void main(String[] args) {
	 	int nOdd = 0;
        for (int i = 1; i <= N; i++) {
            if (divisors(i) % 2 == 1) nOdd++;
        }
        System.out.println(nOdd);
    }

    private static int divisors(int n) {
     	int numDivisors= 0;
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) numDivisors++;
        }
        return numDivisors;
    }
}

